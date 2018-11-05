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

    @Column (name = "booking_rut")
    private Long rut;

    @Column
    private String alias;

    @Column
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "tables_ids")
    private Tables table;

    public Booking() {
    }

    public Booking(long id, Long rut, String alias, Tables table) {
        this.id = id;
        this.rut = this.restaurant.getRut();
        this.alias = alias;
        this.table = table;
        this.confirmed = false;
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

    public void setConfirmed(){
        this.confirmed = true;
    }

    public boolean getConfirmed(){
        return confirmed;
    }
}
