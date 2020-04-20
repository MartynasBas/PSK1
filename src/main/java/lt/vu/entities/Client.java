package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "select a from Client as a")
})

@Table(name = "CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String surname;

    @Size(max = 50)
    @Column(unique = true)
    private String phone;

    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "clients")
    public List<Part> parts;

    @OneToMany(mappedBy = "client")
    private List<Orders> orders = new ArrayList<>();

    @Version
    @Column(name = "optimistic_lock_ver")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, email);
    }


}
