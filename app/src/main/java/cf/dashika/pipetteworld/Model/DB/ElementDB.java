
package cf.dashika.pipetteworld.Model.DB;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;


@Table(name = "ElementDB", id = "_id")
public class ElementDB extends Model {

    public ElementDB(){super();}

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  List<ElementDB> getAllElement() {
        return new Select().all().from(ElementDB.class)
                .execute();
    }

    public List<ValueDB> getValues() {
        return getMany(ValueDB.class, "fElementDB");
    }

}
