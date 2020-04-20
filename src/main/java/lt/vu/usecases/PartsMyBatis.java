package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.model.Supplier;
import lt.vu.mybatis.model.Part;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.PartMapper;
import lt.vu.mybatis.dao.SupplierMapper;
import lt.vu.persistence.PartDAO;
import lt.vu.persistence.SupplierDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PartsMyBatis {
    @Inject
    private PartMapper partMapper;

    @Inject
    private SupplierMapper supplierMapper;



    @Getter @Setter
    private Integer supplierid;
    @Getter
    private List<Part> allParts;
    @Getter @Setter
    private Part newPart = new Part();

    @PostConstruct
    public void init() {
        this.loadParts();
        /*Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer supplierId = Integer.parseInt(requestParameters.get("supplierId"));
        this.supplier = supplierDAO.findOne(supplierId);*/
    }

    @Transactional
    @LoggedInvocation
    public String createNewPart() {
        //Supplier temp = supplierMapper.selectByPrimaryKey(supplierid);
        newPart.setSupplierId(supplierid);
        partMapper.insert(newPart);
        return "index?faces-redirect=true";
    }

    private void loadParts() {
        this.allParts = partMapper.selectAll();
    }

    public String createNewRec() {
        //supplierDAO.persist(newSupplier);
        return "index";
    }
}
