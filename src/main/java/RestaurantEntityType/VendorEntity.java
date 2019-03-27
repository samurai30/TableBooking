package RestaurantEntityType;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "vendors")
public class VendorEntity implements Serializable {


    private Long id;


    private String firstName;
    private String lastName;
    private String email;
    private Long contact;
    private String username;
    private String password;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ven_id")
    public Set<RestaurantEntity> getRestaurantEntitySet() {
        return restaurantEntitySet;
    }

    public void setRestaurantEntitySet(Set<RestaurantEntity> restaurantEntitySet) {
        this.restaurantEntitySet = restaurantEntitySet;
    }

    private Set<RestaurantEntity> restaurantEntitySet;



    @Column(name = "ven_first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "ven_last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "ven_email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ven_contact")
    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    @Column(name = "ven_username",unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "ven_password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Id
    @Column(name = "ven_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
