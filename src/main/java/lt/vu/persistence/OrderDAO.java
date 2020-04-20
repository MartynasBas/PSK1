package lt.vu.persistence;

import lt.vu.entities.Client;
import lt.vu.entities.Order;
import lt.vu.entities.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class OrderDAO {
    @Inject
    private EntityManager em;

    public void persist(Order order){
        this.em.persist(order);
    }

    public List<Order> loadAll() {
        return em.createNamedQuery("Order.findAll", Order.class).getResultList();
    }
    public Order findOne(Integer id){
        return em.find(Order.class, id);
    }

    public Order update(Order order) {
        return em.merge(order);
    }
}
