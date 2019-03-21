package RestaurantEntityType;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "restaurant_table")
public class RestaurantEntity implements Serializable {
    @Id
    @Column(name = "rest_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long Rest_id;

    @Column(name = "rest_name")
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rest_id")
    private Set<TablesEntity> tablesEntities;


    public Set<TablesEntity> getTablesEntities() {
        return tablesEntities;
    }

    public void setTablesEntities(Set<TablesEntity> tablesEntities) {
        this.tablesEntities = tablesEntities;
    }

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




}
