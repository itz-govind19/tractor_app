package admin.myapp.com.authservice.util;

import admin.myapp.com.authservice.entity.Booking;
import admin.myapp.com.authservice.entity.RateTable;

import java.math.BigDecimal;
import java.util.List;

public class RateCalculator {

    public static BigDecimal calculateRate(Booking booking, admin.myapp.com.authservice.entity.Service service, List<RateTable> rateList) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (RateTable rate : rateList) {
            String unitType = rate.getSubUnitType();
            BigDecimal rateAmount = rate.getRateAmount();

            switch (unitType.toUpperCase()) {
                case "ACRE":
                    if (booking.getAcres() != null) {
                        totalAmount = totalAmount.add(rateAmount.multiply(BigDecimal.valueOf(booking.getAcres())));
                    }
                    break;
                case "GUNTA":
                    if (booking.getGuntas() != null) {
                        totalAmount = totalAmount.add(rateAmount.multiply(BigDecimal.valueOf(booking.getGuntas())));
                    }
                    break;
                case "HOUR":
                    if (booking.getHours() != null) {
                        totalAmount = totalAmount.add(rateAmount.multiply(BigDecimal.valueOf(booking.getHours())));
                    }
                    break;
                case "MINUTE":
                    if (booking.getMinutes() != null) {
                        totalAmount = totalAmount.add(rateAmount.multiply(BigDecimal.valueOf(booking.getMinutes())));
                    }
                    break;
                case "KM":
                    if (booking.getKilometers() != null) {
                        totalAmount = totalAmount.add(rateAmount.multiply(BigDecimal.valueOf(booking.getKilometers())));
                    }
                    break;
                case "METER":
                    if (booking.getMeters() != null) {
                        totalAmount = totalAmount.add(rateAmount.multiply(BigDecimal.valueOf(booking.getMeters())));
                    }
                    break;
            }
        }

        return totalAmount;
    }

}
