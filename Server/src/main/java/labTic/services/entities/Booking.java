package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Booking {

    @Id
    @GeneratedValue(generator="booking_ids")
    @GenericGenerator(name="booking_ids", strategy = "increment")
    private long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_rut")
    private Restaurant restaurant;





}
