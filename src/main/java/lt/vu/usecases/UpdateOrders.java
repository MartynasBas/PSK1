package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Order;
import lt.vu.entities.Part;
import lt.vu.entities.Supplier;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.OrderDAO;
import lt.vu.persistence.PartDAO;
import lt.vu.persistence.SupplierDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class UpdateOrders {
    @Getter
    @Setter
    private Order order;

    @Inject
    private OrderDAO orderDAO;
    @Inject
    private PartDAO partDAO;
    @Inject
    private ClientDAO clientDAO;

    @Getter
    @Setter
    private Order newOrder = new Order();
    @Getter
    @Setter
    private Integer partid;
    @Getter @Setter
    private Integer clientid;

    @PostConstruct
    private void init() {
        System.out.println("UpdateOrder INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer orderId = Integer.parseInt(requestParameters.get("orderId"));
        this.order = orderDAO.findOne(orderId);
    }

    @Transactional
    public String updateOrder() {
        try{
            Client client = clientDAO.findOne(clientid);
            newOrder.setClient(client);
            Part part = partDAO.findOne(partid);
            newOrder.setPart(part);
            //newOrder.setId(order.getId());
            orderDAO.update(newOrder);
        } catch (OptimisticLockException e) {
            return "/index.xhtml";// + "&error=optimistic-lock-exception";
        }
        return "/index.xhtml";
    }



    public Order getClub() {
        return order;
    }

    public void setSupplier(Order order) {
        this.order = order;
    }
}
