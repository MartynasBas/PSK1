package lt.vu.persistence;

import lt.vu.entities.Part;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PartDAO {
    @Inject
    private EntityManager em;

    public List<Part> loadAll() {
        return em.createNamedQuery("Part.findAll", Part.class).getResultList();
    }

    public void persist(Part part){
        this.em.persist(part);
    }

    public Part findOne(Integer id){
        return em.find(Part.class, id);
    }

    public Part update(Part part){
        return em.merge(part);
    }
}
