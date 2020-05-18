package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

public abstract class CalculatorService implements Calculator {
    public Integer method(String orderDate) {
        setup();
        return calculate(orderDate);
    }

    public void setup() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }

    }
    public abstract Integer calculate(String orderDate);

}
