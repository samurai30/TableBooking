package RestaurantEntityType;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@Entity
public class CategoryEntity implements Serializable {

    private Long id;
    private String categoryName;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id")
    public Set<TablesEntity> getTablesEntities() {
        return tablesEntities;
    }

    public void setTablesEntities(Set<TablesEntity> tablesEntities) {
        this.tablesEntities = tablesEntities;
    }

    private Set<TablesEntity> tablesEntities;

    @Column(name = "cat_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Id
    @Column(name = "cat_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
