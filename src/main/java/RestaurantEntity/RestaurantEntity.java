package RestaurantEntity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "restaurant_table")
public class RestaurantEntity {
    @Id
    @Column(name = "rest_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long Rest_id;

    public long getRest_id() {
        return Rest_id;
    }

    public void setRest_id(long rest_id) {
        Rest_id = rest_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "rest_name")
    private String name;


    @OneToMany(mappedBy = "restaurant_table")

    private Collection<TablesEntity> tablesEntity;

}
