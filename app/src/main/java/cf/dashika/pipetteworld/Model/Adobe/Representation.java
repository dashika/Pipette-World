
package cf.dashika.pipetteworld.Model.Adobe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Representation implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type = "application/vnd.adobe.colortheme+json";
    @SerializedName("rel")
    @Expose
    private String rel = "primary";
    @SerializedName("colortheme#data")
    @Expose
    private ColorthemeData colorthemeData;
    public final static Parcelable.Creator<Representation> CREATOR = new Creator<Representation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Representation createFromParcel(Parcel in) {
            Representation instance = new Representation();
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.rel = ((String) in.readValue((String.class.getClassLoader())));
            instance.colorthemeData = ((ColorthemeData) in.readValue((ColorthemeData.class.getClassLoader())));
            return instance;
        }

        public Representation[] newArray(int size) {
            return (new Representation[size]);
        }

    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public ColorthemeData getColorthemeData() {
        return colorthemeData == null ? colorthemeData = new ColorthemeData() : colorthemeData;
    }

    public void setColorthemeData(ColorthemeData colorthemeData) {
        this.colorthemeData = colorthemeData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(rel);
        dest.writeValue(colorthemeData);
    }

    public int describeContents() {
        return 0;
    }

}
