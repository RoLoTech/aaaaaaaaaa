package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(generator = "booking_ids")
    @GenericGenerator(name = "booking_ids", strategy = "increment")
    private long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_rut")
    private Restaurant restaurant;

    @Column
    private String alias;

    @ManyToOne
    @JoinColumn(name = "tables_ids")
    private Tables table;

    public Booking() {
    }

    public Booking(long id, Restaurant restaurant, String alias, Tables table) {
        this.id = id;
        this.restaurant = restaurant;
        this.alias = alias;
        this.table = table;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
