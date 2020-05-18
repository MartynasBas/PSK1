package lt.vu.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.concurrent.Future;

@Decorator
public abstract class CalculatorDecorator implements Calculator{
    @Inject
    @Delegate
    @Any
    private Calculator calculator;

    public Integer method(String orderPlaced){

        System.out.println("Longer time calculation in progress");
        try {
            Thread.sleep(10000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        return calculator.method(orderPlaced);
    }


}
