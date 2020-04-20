package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "select a from Orders as a")
})

@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "order_placed")
    private String orderPlaced;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderPlaced, order.orderPlaced);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderPlaced);
    }
}
