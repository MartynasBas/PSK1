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
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Part.findAll", query = "select a from Part as a")
})
@Table(name = "PART")
@Getter
@Setter
@NoArgsConstructor
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @Size(max = 50)
    private String type;

    @ManyToMany
    @JoinTable(name = "RECCOMENDS_PART")
    private List<Client> clients;

    @OneToMany(mappedBy = "part")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id) &&
                Objects.equals(name, part.name) &&
                Objects.equals(type, part.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
