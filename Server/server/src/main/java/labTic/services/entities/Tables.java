package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Tables {

    @Id
    @GeneratedValue(generator = "tables_ids")
    @GenericGenerator(name = "tables_ids", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_rut")
    private Restaurant restaurant;

    @Column
    private Integer capacity;

    @Column
    private String occupant;

    public Tables(Restaurant restaurant, int capacity) {
        this.restaurant = restaurant;
        this.capacity = capacity;

    }

    public String getOccupant() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantRut() {
        return restaurant.getRut();
    }

    public Integer getCapacity() {
        return capacity;
    }
}