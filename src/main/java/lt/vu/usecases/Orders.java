package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Order;
import lt.vu.entities.Part;
import lt.vu.entities.Supplier;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.OrderDAO;
import lt.vu.persistence.PartDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Orders {
    @Inject
    private OrderDAO orderDAO;
    @Inject
    private PartDAO partDAO;
    @Inject
    private ClientDAO clientDAO;

    @Getter
    @Setter
    private Order newOrder = new Order();
    @Getter @Setter
    private Integer partid;
    @Getter @Setter
    private Integer clientid;

    @Getter
    private List<Order> allOrders;

    @PostConstruct
    public void init() {
        this.allOrders = orderDAO.loadAll();
    }
    @Transactional
    @LoggedInvocation
    public String createNewOrder() {
        Client client = clientDAO.findOne(clientid);
        newOrder.setClient(client);
        Part part = partDAO.findOne(partid);
        newOrder.setPart(part);
        orderDAO.persist(newOrder);
        return "index";
    }
}
