package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column(name = "booking_rut")
    private Long rut;

    @Column(name = "booking_alias")
    private String alias;

    @Column(name = "booking_confirmation")
    private Boolean confirmed;

    @Column(name = "booking_rejection")
    private Boolean rejected;

    @Column(name = "booking_finalization")
    private Boolean finished;

    @ManyToOne
    @JoinColumn(name = "tables_ids")
    private Tables table;

    public Booking(long id, Long rut, String alias, Tables table) {
        this.id = id;
        this.rut = rut;
        this.alias = alias;
        this.table = table;
        this.confirmed = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setConfirmed() {
        this.confirmed = true;
    }

    public void setRejected() {
        this.rejected = true;
    }

    public void setFinished() {
        this.finished = true;
        this.restaurant.setCompletedReservations(this.restaurant.getCompletedReservations() + 1);
    }
}
