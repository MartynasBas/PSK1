package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
@Alternative
public class MonthsCalculator extends CalculatorService implements Serializable {

    public Integer calculate(String orderDate) {
//        try {
//            Thread.sleep(3000); // Simulate intensive work
//        } catch (InterruptedException e) {
//        }
//        LocalDate dateBefore = LocalDate.parse(orderDate);
//        LocalDate dateAfter = LocalDate.parse("2020-05-19");

        //calculating number of days in between
        LocalDate dateBefore = LocalDate.parse(orderDate);
        LocalDate dateAfter = LocalDate.parse("2020-05-19");
        Integer noOfMonthsBetween = (int)ChronoUnit.MONTHS.between(dateBefore, dateAfter);


        return noOfMonthsBetween;
        //Integer generatedJerseyNumber = new Random().nextInt(100);
        //return generatedJerseyNumber;
    }
}