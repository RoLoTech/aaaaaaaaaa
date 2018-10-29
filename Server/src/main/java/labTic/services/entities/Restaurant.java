package labTic.services.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    public Restaurant(long rut, String name, String address, String style, String phoneNumber, String area) {
        this.rut = rut;
        this.name = name;
        this.address = address;
        this.style = style;
        this.phone = phoneNumber;
        this.area = area;
    }


    @Id
    @GeneratedValue(generator="restaurant_rut")
    @GenericGenerator(name="restaurant_rut", strategy = "increment")
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

    @Lob
    @Column
    private byte[] profilePicture;

    @Column
    private LocalTime openingTime;

    @Column
    private LocalTime closingTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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