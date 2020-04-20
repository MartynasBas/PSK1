package lt.vu.persistence;

import lt.vu.entities.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SupplierDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Supplier supplier){
        this.em.persist(supplier);
    }

    public Supplier findOne(Integer id) {
        return em.find(Supplier.class, id);
    }

    public List<Supplier> loadAll() {
        return em.createNamedQuery("Supplier.findAll", Supplier.class)
                .getResultList();
    }
    public Supplier update(Supplier supplier) {
        return em.merge(supplier);
    }
}
