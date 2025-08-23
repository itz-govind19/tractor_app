package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.entity.Booking;
import admin.myapp.com.authservice.entity.Queue;

import admin.myapp.com.authservice.repositoy.BookingRepo;
import admin.myapp.com.authservice.repositoy.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueueService {

    @Autowired
    private QueueRepo queueRepository;

    @Autowired
    private BookingRepo bookingRepository;

    /**
     * CREATE → Add booking to queue
     */
    @Transactional
    public Queue createQueue(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Queue queue = new Queue();
        queue.setBooking(booking);
        queue.setAdjusted(false);
        queue.setDeleted(false);

        queue = queueRepository.save(queue);
        recalculatePositions();
        return queue;
    }

    /**
     * READ → Get all queues in sorted order
     */
    public List<Queue> getAllQueues() {
        return queueRepository.findAll()
                .stream()
                .sorted(queueComparator())
                .collect(Collectors.toList());
    }

    /**
     * READ → Get single queue by ID
     */
    public Optional<Queue> getQueueById(Long queueId) {
        return queueRepository.findById(queueId);
    }

    /**
     * UPDATE → Update queue record (e.g., adjust, reschedule)
     */
    @Transactional
    public Queue updateQueue(Long queueId, Queue updatedQueue) {
        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        queue.setAdjusted(updatedQueue.isAdjusted());
        queue.setScheduledDate(updatedQueue.getScheduledDate());
        queue.setDeleted(updatedQueue.isDeleted());

        // Update Booking if passed (optional)
        if (updatedQueue.getBooking() != null) {
            queue.setBooking(updatedQueue.getBooking());
        }

        Queue saved = queueRepository.save(queue);
        recalculatePositions();
        return saved;
    }

    /**
     * DELETE → Mark queue as deleted (soft delete)
     */
    @Transactional
    public void deleteQueue(Long queueId) {
        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        queue.setDeleted(true);
        queueRepository.save(queue);

        recalculatePositions();
    }

    /**
     * Hard delete (optional)
     */
    @Transactional
    public void hardDeleteQueue(Long queueId) {
        if (!queueRepository.existsById(queueId)) {
            throw new RuntimeException("Queue not found");
        }
        queueRepository.deleteById(queueId);
    }

    /**
     * Comparator logic for queue sorting
     */
    private Comparator<Queue> queueComparator() {
        return Comparator
                // Paid first (PAID -> 0, else 1)
                .comparingInt((Queue q) ->
                        "PAID".equalsIgnoreCase(q.getBooking().getStatus()) ? 0 : 1
                )
                // Then by paymentTime (earlier payments first), fall back to createdAt
                .thenComparing(q -> {
                    LocalDateTime paymentTime = q.getBooking().getPaymentTime();
                    return paymentTime != null ? paymentTime : q.getBooking().getExpectedDate();
                })
                // Finally by bookingId (lower ID = earlier booking)
                .thenComparing(q -> q.getBooking().getBookingId());
    }

    /**
     * Recalculate queue positions
     */
    @Transactional
    public void recalculatePositions() {
        List<Queue> sortedQueues = queueRepository.findAll()
                .stream()
                .sorted(queueComparator())
                .collect(Collectors.toList());

        int pos = 1;
        for (Queue q : sortedQueues) {
            q.setPositionNo(pos++);
            queueRepository.save(q);
        }
    }
}
