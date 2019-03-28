package RestaurantEntityType;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "restaurant_table")
public class RestaurantEntity implements Serializable {

    private long Rest_id;
    @Size(max = 20, min = 3, message = "Name is too short")
    @NotEmpty(message = "Please enter name")
    private String name;

    @NotEmpty(message = "Please enter location")
    private String location;

    @NotNull(message = "Please Enter Ratings")
    private int startRating;

    @NotEmpty(message = "Please enter Landmark")
    private String landmark;


    @Column(name = "ven_id")
    public long getVen_id() {
        return ven_id;
    }

    public void setVen_id(long ven_id) {
        this.ven_id = ven_id;
    }

    private Set<TablesEntity> tablesEntities;

    private String verified;


    private long ven_id;


    @Column(name = "verification")
    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    @Column(name = "rest_location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "rest_rating")
    public int getStartRating() {
        return startRating;
    }

    public void setStartRating(int startRating) {
        this.startRating = startRating;
    }

    @Column(name = "rest_landmark")
    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rest_id")
    public Set<TablesEntity> getTablesEntities() {
        return tablesEntities;
    }

    public void setTablesEntities(Set<TablesEntity> tablesEntities) {
        this.tablesEntities = tablesEntities;
    }


    @Id
    @Column(name = "rest_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    public long getRest_id() {
        return Rest_id;
    }


    public void setRest_id(long rest_id) {
        Rest_id = rest_id;
    }


    @Column(name = "rest_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
