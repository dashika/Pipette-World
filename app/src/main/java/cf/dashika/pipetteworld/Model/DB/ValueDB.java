
package cf.dashika.pipetteworld.Model.DB;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import cf.dashika.pipetteworld.Model.Adobe.Value;

@Table(name = "ValueDB", id = "_id")
public class ValueDB extends Model {

    public ValueDB() {
        super();
    }

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

    @Column(name = "fElementDB", onDelete = Column.ForeignKeyAction.CASCADE)
    private ElementDB element;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    @Column(name = "r")
    private int r;
    @Column(name = "b")
    private int b;
    @Column(name = "g")
    private int g;

    public ValueDB migrateFromValue(Value value) {
        this.r = value.getR();
        this.g = value.getG();
        this.b = value.getB();
        return this;
    }




}
