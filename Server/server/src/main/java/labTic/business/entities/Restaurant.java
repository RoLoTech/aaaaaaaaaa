package labTic.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    public Restaurant(long rut, String name, String address, String style, String phoneNumber, String area, String food, String price) {
        this.rut = rut;
        this.name = name;
        this.address = address;
        this.style = style;
        this.phone = phoneNumber;
        this.area = area;
        this.foodtype = food;
        this.priceRange = price;
        this.availability = false;
    }

    public Restaurant() {
    }

    @Id
    @Column(name = "restaurant_rut", nullable = false, unique = true)
    private Long rut;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "restaurant_address")
    private String address;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "restaurant_phone")
    private String phone;

    @Column(name = "restaurant_area")
    private String area;

    @Column(name = "restaurant_capacity")
    private Integer capacity;

    @Column(name = "restaurant_food_type")
    private String foodtype;

    @Column(name = "restaurant_style")
    private String style;

    @Column(name = "restaurant_price_range")
    private String priceRange;

    @Column(name = "restaurant_rating")
    private Float rating;

    @Column(name = "restaurant_completed_reservations")
    private Integer completedReservations;

    @Column(name = "restaurant_password")
    private String password;

    @Column(name = "restaurant_monday")
    private String hoursMonday;

    @Column(name = "restaurant_tuesday")
    private String hoursTuesday;

    @Column(name = "restaurant_wednesday")
    private String hoursWednesday;

    @Column(name = "restaurant_thursday")
    private String hoursThursday;

    @Column(name = "restaurant_friday")
    private String hoursFriday;

    @Column(name = "restaurant_saturday")
    private String hoursSaturday;

    @Column(name = "restaurant_sunday")
    private String hoursSunday;

    @Column(name = "restaurant_description")
    private String description;

    @Column(name = "restaurant_availability")
    private Boolean availability;

    @Lob
    @Column(name = "restaurant_profile_picture")
    private byte[] profilePicture;

    @Lob
    @Column(name = "restaurant_picture_1")
    private byte[] restaurantPicture1;

    @Lob
    @Column(name = "restaurant_picture_2")
    private byte[] restaurantPicture2;

    @Lob
    @Column(name = "restaurant_picture_3")
    private byte[] restaurantPicture3;

    @Lob
    @Column(name = "restaurant_picture_4")
    private byte[] restaurantPicture4;


    @ManyToOne
    @JoinColumn(name = "tables_ids")
    private Tables table;



    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Long getRut() {
        return rut;
    }

    public String getArea() {
        return area;
    }

    public String getStyle() {
        return style;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setId(long rut) {
        this.rut = rut;
    }

    public Integer getCompletedReservations() {
        return completedReservations;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFood_type(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", foodtype='" + foodtype + '\'' +
                ", priceRange='" + priceRange + '\'' +
                ", rating=" + rating +
                '}';
    }


    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHoursMonday() {
        return hoursMonday;
    }

    public String getHoursTuesday() {
        return hoursTuesday;
    }

    public String getHoursWednesday() {
        return hoursWednesday;
    }

    public String getHoursThursday() {
        return hoursThursday;
    }

    public String getHoursFriday() {
        return hoursFriday;
    }

    public String getHoursSaturday() {
        return hoursSaturday;
    }

    public String getHoursSunday() {
        return hoursSunday;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setCompletedReservations(int completedReservations) {
        this.completedReservations = completedReservations;
    }

    public void setHoursMonday(String monday) {
        this.hoursMonday = monday;
    }

    public void setHoursTuesday(String hoursTuesday) {
        this.hoursTuesday = hoursTuesday;
    }

    public void setHoursWednesday(String hoursWednesday) {
        this.hoursWednesday = hoursWednesday;
    }

    public void setHoursThursday(String hoursThursday) {
        this.hoursThursday = hoursThursday;
    }

    public void setHoursFriday(String hoursFriday) {
        this.hoursFriday = hoursFriday;
    }

    public void setHoursSaturday(String hoursSaturday) {
        this.hoursSaturday = hoursSaturday;
    }

    public void setHoursSunday(String hoursSunday) {
        this.hoursSunday = hoursSunday;
    }

    public byte[] getRestaurantPicture1() {
        return restaurantPicture1;
    }

    public void setRestaurantPicture1(byte[] restaurantPicture1) {
        this.restaurantPicture1 = restaurantPicture1;
    }

    public byte[] getRestaurantPicture2() {
        return restaurantPicture2;
    }

    public void setRestaurantPicture2(byte[] restaurantPicture2) {
        this.restaurantPicture2 = restaurantPicture2;
    }

    public byte[] getRestaurantPicture3() {
        return restaurantPicture3;
    }

    public void setRestaurantPicture3(byte[] restaurantPicture3) {
        this.restaurantPicture3 = restaurantPicture3;
    }

    public byte[] getRestaurantPicture4() {
        return restaurantPicture4;
    }

    public void setRestaurantPicture4(byte[] restaurantPicture4) {
        this.restaurantPicture4 = restaurantPicture4;
    }


    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }
}