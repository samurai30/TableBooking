package RestaurantEntityType;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rest_tables")
public class TablesEntity implements Serializable {

    private long id;
    private long rest_id;
    private long cat_id;

    @Column(name = "table_status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;


    @Column(name = "cat_id")
    public long getCat_id() {
        return cat_id;
    }

    public void setCat_id(long cat_id) {
        this.cat_id = cat_id;
    }

    @Column(name = "rest_id")
    public long getRest_id() {
        return rest_id;
    }

    public void setRest_id(long rest_id) {
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
