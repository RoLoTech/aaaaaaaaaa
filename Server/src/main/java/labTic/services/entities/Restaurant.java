package labTic.services.entities;


import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    private boolean[] tables;

    public Restaurant() {
    }


    public Restaurant(long rut, String name, String adress) {
        this.rut = rut;
        this.name = name;
        this.address = adress;
    }


    @Id
    @Column(name = "restaurant_rut", nullable = false, unique = true)
    private long rut;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "restaurant_address")
    private String address;

    @Column(name = "restaurant_phone")
    private String phone;

    @Column(name = "restaurant_area")
    private String area;

    @Column(name = "restaurant_capacity")
    private int capacity;

    @Column(name = "restaurant_food_type")
    private String foodtype;

    @Column(name = "restaurant_style")
    private String style;

    @Column(name = "restaurant_price_range")
    private String priceRange;

    @Column(name = "restaurant_rating")
    private float rating;

    @Column(name = "restaurant_completed_reservations")
    private int completedReservations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRut() {
        return rut;
    }

    public void setId(long rut) {
        this.rut = rut;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFood_type(String foodtype) {
        this.foodtype = foodtype;
    }

//    public void bookTable(int table) throws InvalidTableException, TableAlreadyInUseException {
//        if (table - 1 < 0 || table - 1 < this.capacity) {
//            throw new InvalidTableException();
//        }
//        if (this.tables[table] == false) {
//            this.tables[table] = true;
//        } else {
//            throw new TableAlreadyInUseException();
//        }
//    }
//
//    public void releaseTable(int table) throws InvalidTableException, TableAlreadyReleasedException {
//        if (table - 1 < 0 || table - 1 < this.capacity) {
//            throw new InvalidTableException();
//        }
//        if (this.tables[table] == true) {
//            this.tables[table] = false;
//        } else {
//            throw new TableAlreadyReleasedException();
//        }
//    }
}