package lt.vu.persistence;

import lt.vu.entities.Orders;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class OrderDAO {
    @Inject
    private EntityManager em;

    public void persist(Orders order){
        this.em.persist(order);
    }

    public List<Orders> loadAll() {
        return em.createNamedQuery("Order.findAll", Orders.class).getResultList();
    }
    public Orders findOne(Integer id){
        return em.find(Orders.class, id);
    }

    public Orders update(Orders order) {
        return em.merge(order);
    }
}
