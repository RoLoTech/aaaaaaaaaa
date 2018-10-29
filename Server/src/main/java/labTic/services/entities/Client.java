package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(generator="clients_ids")
    @GenericGenerator(name="clients_ids", strategy = "increment")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String user;

    private String password;

    private String phoneNumber;

    private Float rating;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String user, String password, String phone_number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.user = user;
        this.password = password;
        this.phoneNumber = phone_number;
    }

    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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