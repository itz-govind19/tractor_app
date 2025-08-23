package admin.myapp.com.authservice.controller;

import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.entity.Queue;
import admin.myapp.com.authservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL+Constants.BASE_URL_QUEUE)
public class QueueController {

    @Autowired
    private QueueService queueService;

    // CREATE
    @PostMapping("/create/{bookingId}")
    public ResponseEntity<Queue> createQueue(@PathVariable Long bookingId) {
        return ResponseEntity.ok(queueService.createQueue(bookingId));
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Queue>> getAllQueues() {
        return ResponseEntity.ok(queueService.getAllQueues());
    }

    // READ by id
    @GetMapping("/{queueId}")
    public ResponseEntity<Queue> getQueueById(@PathVariable Long queueId) {
        return queueService.getQueueById(queueId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{queueId}")
    public ResponseEntity<Queue> updateQueue(@PathVariable Long queueId, @RequestBody Queue queue) {
        return ResponseEntity.ok(queueService.updateQueue(queueId, queue));
    }

    // DELETE (soft)
    @DeleteMapping("/{queueId}")
    public ResponseEntity<String> deleteQueue(@PathVariable Long queueId) {
        queueService.deleteQueue(queueId);
        return ResponseEntity.ok("Queue marked as deleted");
    }

    // DELETE (hard)
    @DeleteMapping("/hard/{queueId}")
    public ResponseEntity<String> hardDeleteQueue(@PathVariable Long queueId) {
        queueService.hardDeleteQueue(queueId);
        return ResponseEntity.ok("Queue permanently deleted");
    }

    // RECALCULATE manually
    @PostMapping("/recalculate")
    public ResponseEntity<String> recalcQueue() {
        queueService.recalculatePositions();
        return ResponseEntity.ok("Queue recalculated successfully");
    }
}
