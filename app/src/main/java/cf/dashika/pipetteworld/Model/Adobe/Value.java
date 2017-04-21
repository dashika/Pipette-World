
package cf.dashika.pipetteworld.Model.Adobe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value implements Parcelable {

    @SerializedName("r")
    @Expose
    private Integer r;
    @SerializedName("b")
    @Expose
    private Integer b;
    @SerializedName("g")
    @Expose
    private Integer g;
    public final static Parcelable.Creator<Value> CREATOR = new Creator<Value>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Value createFromParcel(Parcel in) {
            Value instance = new Value();
            instance.r = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.b = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.g = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public Value[] newArray(int size) {
            return (new Value[size]);
        }

    };

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(r);
        dest.writeValue(b);
        dest.writeValue(g);
    }

    public int describeContents() {
        return 0;
    }

}
