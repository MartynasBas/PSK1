package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Supplier;
import lt.vu.persistence.SupplierDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Suppliers {
    @Inject
    private SupplierDAO supplierDAO;

    @Getter
    @Setter
    private Supplier newSupplier = new Supplier();

    @Getter
    private List<Supplier> allSuppliers;

    @PostConstruct
    public void init(){
        this.loadSupplier();
    }

    @Transactional
    public String createNewSupplier() {
        supplierDAO.persist(newSupplier);
        return "index";
    }
    private void loadSupplier() {
        this.allSuppliers = supplierDAO.loadAll();
    }
}
