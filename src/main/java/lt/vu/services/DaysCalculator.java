package lt.vu.services;

import lt.vu.entities.Orders;
import lt.vu.persistence.OrderDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class DaysCalculator implements Serializable {

    public Integer calculateDays(String orderDate) {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        LocalDate dateBefore = LocalDate.parse(orderDate);
        LocalDate dateAfter = LocalDate.parse("2020-05-19");

        //calculating number of days in between
        Integer noOfDaysBetween = (int)ChronoUnit.DAYS.between(dateBefore, dateAfter);


        return noOfDaysBetween;
        //Integer generatedJerseyNumber = new Random().nextInt(100);
        //return generatedJerseyNumber;
    }
}