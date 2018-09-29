package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(generator="clients_ids")
    @GenericGenerator(name="clients_ids", strategy = "increment")
    public long id;

    public String firstName;

    public String lastName;

    public String email;


    public Client() {
    }


    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private String password;


    private String phone_number;

    private Float rating;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}