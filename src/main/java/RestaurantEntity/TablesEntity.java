package RestaurantEntity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "rest_tables")
public class TablesEntity implements Serializable {
    @Id
    @Column(name = "table_id")
    @GeneratedValue
    private long id;

    @Column(name = "table_type")
    private String type;


    @Column(name = "rest_id")
    private int rest_id;


    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
