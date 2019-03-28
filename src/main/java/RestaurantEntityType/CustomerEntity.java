package RestaurantEntityType;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable {

        private Long id;

        @Id
        @Column(name = "cust_id")
        @GeneratedValue(generator = "incrementor")
        @GenericGenerator(name = "incrementor", strategy = "increment")
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @NotEmpty(message = "Enter name please")
        @Size(min = 5,message = "Length should be greater than 5")
        private String username;

        @NotEmpty(message = "Enter Password")
        @Size(min = 5,message = "Length should be greater than 5")
        private String password;
        @NotNull(message = "Enter Contact")
        @Pattern(regexp = "(^$|[0-9]{10})",message = "Should be a Number of 10 digits")
        private String contact;
        @Email(message = "Please Enter Valid Email")
        @NotEmpty(message = "Enter Email")
        private String email;
        @NotEmpty(message = "Enter First Name")
        @Size(max = 20,min = 3,message = "Too short")
        private String fName;
        @NotEmpty(message = "Enter Last Name")
        @Size(max = 20,min = 3,message = "Too short")
        private String lName;


        @Column(name = "cust_username",unique = true)
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Column(name = "cust_password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        @Column(name = "cust_contact")
        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        @Column(name = "cust_email",unique = true)
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Column(name = "cust_fName")
        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }

        @Column(name = "cust_lName")
        public String getlName() {
            return lName;
        }

        public void setlName(String lName) {
            this.lName = lName;
        }




}
