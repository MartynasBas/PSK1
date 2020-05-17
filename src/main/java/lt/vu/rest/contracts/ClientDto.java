package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Part;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter @Setter
public class ClientDto {

    private String name;

    private String surname;

    private String phone;

    private String email;

}
