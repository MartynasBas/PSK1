package lt.vu.rest;

import lombok.*;
import lt.vu.entities.Client;
import lt.vu.entities.Orders;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.OrderDAO;
import lt.vu.rest.contracts.ClientDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/clients")
public class ClientsController {

    @Inject
    @Setter @Getter
    private ClientDAO clientDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Client client = clientDAO.findOne(id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());

        return Response.ok(clientDto).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ClientDto clientDto) {
        try {
            Client newClient = new Client();
            newClient.setName(clientDto.getName());
            newClient.setSurname(clientDto.getSurname());
            newClient.setEmail(clientDto.getEmail());
            newClient.setPhone(clientDto.getPhone());
            clientDAO.persist(newClient);
            return Response.ok().build();
        } catch (OptimisticLockException | PessimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer clientId,
            ClientDto clientdata) {
        try {
            Client existingClient = clientDAO.findOne(clientId);
            if (existingClient == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingClient.setName(clientdata.getName());
            existingClient.setSurname(clientdata.getSurname());
            existingClient.setEmail(clientdata.getEmail());
            existingClient.setPhone(clientdata.getPhone());
            clientDAO.update(existingClient);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }


}
