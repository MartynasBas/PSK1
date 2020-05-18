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
public class DaysCalculator extends CalculatorService implements Serializable {

    public Integer calculate(String orderDate) {

        LocalDate dateBefore = LocalDate.parse(orderDate);
        LocalDate dateAfter = LocalDate.parse("2020-05-01");
        Integer noOfDaysBetween = (int)ChronoUnit.DAYS.between(dateBefore, dateAfter);


        return noOfDaysBetween;

    }
}