package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.model.Supplier;
import lt.vu.mybatis.dao.SupplierMapper;
import lt.vu.persistence.SupplierDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class SuppliersMyBatis {
    @Inject
    private SupplierMapper supplierMapper;

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
        supplierMapper.insert(newSupplier);
        return "/myBatis/index?faces-redirect=true";
    }
    private void loadSupplier() {
        this.allSuppliers = supplierMapper.selectAll();
    }

    public String loadSupplierOne(Integer id) {
        return supplierMapper.selectByPrimaryKey(id).getName();
    }

}
