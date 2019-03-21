package RestaurantEntity;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rest_tables")
public class TablesEntity {
    @Id
    @Column(name = "table_id")
    private long id;

    @Column(name = "table_type")
    private String type;



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
