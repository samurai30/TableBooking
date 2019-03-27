package RestaurantEntityType;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rest_tables")
public class TablesEntity implements Serializable {

    private long id;
    private int rest_id;
    private int cat_id;


    @Column(name = "cat_id")
    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    @Column(name = "rest_id")
    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }


    @Id
    @Column(name = "table_id")
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
