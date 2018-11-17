package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Tables {

    @Id
    @GeneratedValue(generator = "tables_ids")
    @GenericGenerator(name = "tables_ids", strategy = "increment")
    private Long id;

    @Column
    private Long restaurantRut;

    @Column
    private Integer capacity;

    @Column
    private String occupant;

    public Tables(Long restaurantRut, int capacity) {
        this.restaurantRut = restaurantRut;
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
        return restaurantRut;
    }

    public Integer getCapacity() {
        return capacity;
    }
}