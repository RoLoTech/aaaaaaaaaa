package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

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

    private Long restaurantRut;

    private Integer capacity;

    private String occupant;

    private LocalTime startReservation;

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

    public void setStartReservation(LocalTime startReservation) {
        this.startReservation = startReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}