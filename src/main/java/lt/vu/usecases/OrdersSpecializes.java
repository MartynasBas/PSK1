package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Part;
import lt.vu.interceptors.NotFoundInvocation;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.OrderDAO;
import lt.vu.persistence.PartDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
@Specializes
public class OrdersSpecializes extends Orders implements Serializable {
    @Inject
    private OrderDAO orderDAO;
    @Inject
    private PartDAO partDAO;
    @Inject
    private ClientDAO clientDAO;

    @Getter
    @Setter
    private lt.vu.entities.Orders newOrder = new lt.vu.entities.Orders();
    @Getter @Setter
    private Integer partid;
    @Getter @Setter
    private Integer clientid;


    @Getter
    private List<lt.vu.entities.Orders> allOrders;

    @PostConstruct
    public void init(){
        this.allOrders = orderDAO.loadAll();

    }
    @Transactional
    @NotFoundInvocation
    public String createNewOrder() {
        Client client = clientDAO.findOne(clientid);
        newOrder.setClient(client);
        Part part = partDAO.findOne(partid);
        newOrder.setPart(part);
        orderDAO.persist(newOrder);
        orderDAO.persist(newOrder);
        return "index?faces-redirect=true";
    }




}
