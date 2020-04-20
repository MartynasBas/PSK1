package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Orders;
import lt.vu.entities.Part;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.OrderDAO;
import lt.vu.persistence.PartDAO;

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
    private Orders order;

    @Inject
    private OrderDAO orderDAO;
    @Inject
    private PartDAO partDAO;
    @Inject
    private ClientDAO clientDAO;

    @Getter
    @Setter
    private Orders newOrder = new Orders();
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
        System.out.println(orderId);
    }

    @Transactional
    @LoggedInvocation
    public String updateOrder() {
        try{
            Client client = clientDAO.findOne(clientid);
            newOrder.setClient(client);
            Part part = partDAO.findOne(partid);
            newOrder.setPart(part);
            newOrder.setId(order.getId());
            //System.out.println("UpdateOrder INIT CALLED");
            orderDAO.update(newOrder);
        } catch (OptimisticLockException e) {
            return "/index.xhtml";// + "&error=optimistic-lock-exception";
        }
        return "/index.xhtml";
    }



    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
