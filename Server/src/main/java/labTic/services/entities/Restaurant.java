package labTic.services.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    private boolean[] tables;

    public Restaurant() {
    }
 /* String name = firstName.getText();
    String style = lastName.getText();
    String sPhoneNumber = phoneNumber.getText();
    String address = pass.getText();
    String area = email.getText();
*/

    public Restaurant(long rut, String name, String address, String style, String phoneNumber, String area, String food, String price) {
        this.rut = rut;
        this.name = name;
        this.address = address;
        this.style = style;
        this.phone = phoneNumber;
        this.area = area;
        this.foodtype = food;
        this.priceRange = price;
    }


    @Id
    @Column (name = "restaurant_rut", nullable = false, unique = true)
    private Long rut;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "restaurant_address")
    private String address;

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

    @Column (name = "restaurant_description")
    private String description;

    @Column (name = "restaurant_availability")
    private Boolean availability;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    @Column (name = "restaurant_profile_picture")
    private byte[] profilePicture;

//    @Lob
//    @Column(name="pic")
//    private byte[] pic;

//    @Column (name = "restaurant_opening_time")
//    private LocalTime openingTime;
//
//    @Column (name = "restaurant_closing_time")
//    private LocalTime closingTime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public LocalTime getOpeningTime() {
//        return openingTime;
//    }
//
//    public void setOpeningTime(LocalTime openingTime) {
//        this.openingTime = openingTime;
//    }

//    public LocalTime getClosingTime() {
//        return closingTime;
//    }
//
//    public void setClosingTime(LocalTime closingTime) {
//        this.closingTime = closingTime;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress(){
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

    public Float getRating() {
        return rating;
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

    public String getPhone(){ return phone; }

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

    public void setHoursMonday(String hoursMonday) {
        this.hoursMonday = hoursMonday;
    }

    public String getHoursTuesday() {
        return hoursTuesday;
    }

    public void setHoursTuesday(String hoursTuesday) {
        this.hoursTuesday = hoursTuesday;
    }

    public String getHoursWednesday() {
        return hoursWednesday;
    }

    public void setHoursWednesday(String hoursWednesday) {
        this.hoursWednesday = hoursWednesday;
    }

    public String getHoursThursday() {
        return hoursThursday;
    }

    public void setHoursThursday(String hoursThursday) {
        this.hoursThursday = hoursThursday;
    }

    public String getHoursFriday() {
        return hoursFriday;
    }

    public void setHoursFriday(String hoursFriday) {
        this.hoursFriday = hoursFriday;
    }

    public String getHoursSaturday() {
        return hoursSaturday;
    }

    public void setHoursSaturday(String hoursSaturday) {
        this.hoursSaturday = hoursSaturday;
    }

    public String getHoursSunday() {
        return hoursSunday;
    }

    public void setHoursSunday(String hoursSunday) {
        this.hoursSunday = hoursSunday;
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