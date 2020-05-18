package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@ApplicationScoped
@Specializes
public class DaysUpdatedCalculator extends DaysCalculator{
    public Integer calculate(String orderDate) {
        LocalDate dateBefore = LocalDate.parse(orderDate);
        LocalDate dateAfter = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //LocalDate dateAfter = LocalDate.parse("2020-05-01");
        Integer noOfDaysBetween = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter);


        return noOfDaysBetween;
    }
}
