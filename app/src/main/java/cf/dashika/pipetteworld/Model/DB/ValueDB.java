
package cf.dashika.pipetteworld.Model.DB;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import cf.dashika.pipetteworld.Model.Adobe.Value;

@Table(name = "ValueDB", id = "_id")
public class ValueDB extends Model {

    public ValueDB(ElementDB element) {
        super();
        this.element = element;
    }

    public ElementDB getElement() {
        return element;
    }

    public void setElement(ElementDB element) {
        this.element = element;
    }

    @Column(name = "fElement", onDelete = Column.ForeignKeyAction.CASCADE)
    private ElementDB element;

    @Column(name = "r")
    private Integer r;
    @Column(name = "b")
    private Integer b;
    @Column(name = "g")
    private Integer g;

    public ValueDB migrateFromValue(Value value) {
        this.r = value.getR();
        this.g = value.getG();
        this.b = value.getB();
        return this;
    }


    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

}
