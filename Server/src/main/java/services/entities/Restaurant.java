package services.entities;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "restaurant_rut")
    private long rut;

    @Column(name = "restaurant_name")
    private String name;

    @Column (name = "restaurant_address")
    private String address;

    @Column (name = "restaurant_phone")
    private String phone;

    @Column (name = "restaurant_area")
    private String area;

    @Column (name = "restaurant_capacity")
    private int capacity;

    @Column (name = "restaurant_food_type")
    private String food_type;

    @Column (name = "restaurant_style")
    private String style;

    @Column (name = "restaurant_price_range")
    private String priceRange;

    @Column (name = "restaurant_rating")
    private float rating;

    @Column (name = "restaurant_completed_reservations")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRut(long rut) {
        this.rut = rut;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCompletedReservations() {
        return completedReservations;
    }

    public void setCompletedReservations(int completedReservations) {
        this.completedReservations = completedReservations;
    }
}