package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Tables {
    @Id
    @GeneratedValue(generator="tables_ids")
    @GenericGenerator(name="tables_ids", strategy = "increment")
    private Long id;

    private Long restaurantRut;

    private Integer capacity;

    private String occupant;

    private LocalTime startReservation;

    public Tables() {
    }


    public Tables(Long restaurantRut, int capacity) {
        this.restaurantRut=restaurantRut;
        this.capacity=capacity;

    }

    public Tables(Long restaurantRut) {
        this.restaurantRut=restaurantRut;

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRestaurantRut() {
        return restaurantRut;
    }

    public void setRestaurantRut(long restaurantRut) {
        this.restaurantRut = restaurantRut;
    }

    public String getOccupant() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    public LocalTime getStartReservation() {
        return startReservation;
    }

    public void setStartReservation(LocalTime startReservation) {
        this.startReservation = startReservation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}