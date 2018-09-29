package labTic.services.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(generator="clients_ids")
    @GenericGenerator(name="clients_ids", strategy = "increment")
    public long id;

    public long document;

    public String name;

    public String address;

    public Client(long document, String name, String address) {
        this.document = document;
        this.name = name;
        this.address = address;
    }


    private String password;

    private String firstName;

    private String lastName;

    private String mail;

    private Integer age;
    //Cambiar a DateTime para que sea nacimiento

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}