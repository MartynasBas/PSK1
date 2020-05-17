package lt.vu.usecases;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.DaysCalculator;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class Days implements Serializable {
    @Inject
    DaysCalculator daysCalculator;

    private CompletableFuture<Integer> daysCalculatorTask = null;

    Map<String, CompletableFuture<Integer>> daysTasks = new HashMap<>();

    @LoggedInvocation
    public String recalculateDays() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        //daysCalculatorTask = CompletableFuture.supplyAsync(() -> daysCalculator.calculateDays(requestParameters.get("orderPlaced")));
        daysTasks.put(requestParameters.get("orderId"), CompletableFuture.supplyAsync(() -> daysCalculator.calculateDays(requestParameters.get("orderPlaced"))));
        return  "orders?faces-redirect=true&orderId=" + requestParameters.get("orderId");
    }

    public boolean daysCalculationRunning(String orderId) {
        return daysTasks.get(orderId) != null && !daysTasks.get(orderId).isDone();
    }

    public String getDaysCalculatorStatus(String orderId) throws ExecutionException, InterruptedException {
        if (daysTasks.get(orderId) == null) {
            return "  Days have not been calculated.";
        } else if (daysCalculationRunning(orderId)) {
            return "  Days are being calculated...";
        }
        return "  Total days from order: " + daysTasks.get(orderId).get();
    }

}
