package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

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

    @Column(name = "booking_start_date")
    private LocalTime startDate;

    @Column(name = "booking_assistants")
    private Integer assistants;

    @Column(name = "booking_confirmation")
    private Boolean confirmed;

    @Column(name = "booking_rejection")
    private Boolean rejected;

    @Column(name = "booking_finalization")
    private Boolean finished;

    @ManyToOne
    @JoinColumn(name = "tables_ids")
    private Tables table;

    public Booking(){}

    public Booking(Long id, Long rut, String alias, Tables table) {
        this.id = id;
        this.rut = rut;
        this.alias = alias;
        this.table = table;
        this.confirmed = false;
        //cambios
        this.finished = false;
        this.rejected = false;
    }

    public Booking(Long id, Long rut, String alias, Integer assistants, LocalTime startDate){
        this.id = id;
        this.rut = rut;
        this.alias = alias;
        this.assistants = assistants;
        this.startDate = startDate;
        this.confirmed = false;
        this.rejected = false;
        this.finished = false;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Long getRut() {
        return rut;
    }

    public String getAlias() {
        return alias;
    }

    public LocalTime getStartDate() {
        return startDate;
    }

    public Integer getAssistants() {
        return assistants;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public Boolean getFinished() {
        return finished;
    }

    public Tables getTable() {
        return table;
    }
}
