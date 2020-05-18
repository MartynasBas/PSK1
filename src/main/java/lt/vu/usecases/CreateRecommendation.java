package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Part;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.interceptors.NotFoundInvocation;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.PartDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class CreateRecommendation {
    @Inject
    private PartDAO partDAO;
    @Inject
    private ClientDAO clientDAO;
    @Getter
    @Setter
    private Integer partid;
    @Getter @Setter
    private Integer clientid;

    @Getter @Setter
    private Part part;

    @Getter @Setter
    private Client client;

    @PostConstruct
    private void init() {
    }

    @Transactional
    @NotFoundInvocation
    public String createNewRec() {
        part = partDAO.findOne(partid);
        client = clientDAO.findOne(clientid);
        client.getClass();
        part.getClients().add(client);
        return "index";
    }

}
