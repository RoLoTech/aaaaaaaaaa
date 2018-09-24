package labTic.services.entities;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column (name = "client_password", nullable = false)
    private String password;

    @Column(name = "client_first_name", nullable = false)
    private String firstName;

    @Column(name = "client_last_name", nullable = false)
    private String lastName;

    @Column (name = "client_mail", nullable = false)
    private String mail;

    @Column(name = "client_age", nullable = false)
    private Integer age;
    //Cambiar a DateTime para que sea nacimiento

    @Column (name = "client_phone_number")
    private String phone_number;

    @Column (name = "client_rating")
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