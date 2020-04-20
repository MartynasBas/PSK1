package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Part;
import lt.vu.entities.Supplier;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PartDAO;
import lt.vu.persistence.SupplierDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Parts {

    @Inject
    private PartDAO partDAO;

    @Inject
    private SupplierDAO supplierDAO;

    @Getter @Setter
    private Supplier supplier;

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
        Supplier temp = supplierDAO.findOne(supplierid);
        newPart.setSupplier(temp);
        partDAO.persist(newPart);
        return "index";
    }

    private void loadParts() {
        this.allParts = partDAO.loadAll();
    }
}
