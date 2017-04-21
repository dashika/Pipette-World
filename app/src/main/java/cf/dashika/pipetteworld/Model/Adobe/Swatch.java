
package cf.dashika.pipetteworld.Model.Adobe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Swatch implements Parcelable {

    @SerializedName("mode")
    @Expose
    private String mode = "RGB";
    @SerializedName("value")
    @Expose
    private Value value;
    public final static Parcelable.Creator<Swatch> CREATOR = new Creator<Swatch>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Swatch createFromParcel(Parcel in) {
            Swatch instance = new Swatch();
            instance.mode = ((String) in.readValue((String.class.getClassLoader())));
            instance.value = ((Value) in.readValue((Value.class.getClassLoader())));
            return instance;
        }

        public Swatch[] newArray(int size) {
            return (new Swatch[size]);
        }

    };

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mode);
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}
