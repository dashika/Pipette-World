
package cf.dashika.pipetteworld.Model.Adobe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ColorthemeData implements Parcelable {

    @SerializedName("mood")
    @Expose
    private String mood = "custom";
    @SerializedName("baseSwatchIndex")
    @Expose
    private Integer baseSwatchIndex = 2;
    @SerializedName("rule")
    @Expose
    private String rule = "triad";
    @SerializedName("swatches")
    @Expose
    private List<List<Swatch>> swatches = null;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = new ArrayList<Object>() {{
        add("456");
    }};
    public final static Parcelable.Creator<ColorthemeData> CREATOR = new Creator<ColorthemeData>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ColorthemeData createFromParcel(Parcel in) {
            ColorthemeData instance = new ColorthemeData();
            instance.mood = ((String) in.readValue((String.class.getClassLoader())));
            instance.baseSwatchIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.rule = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.swatches, (Swatch.class.getClassLoader()));
            in.readList(instance.tags, (java.lang.Object.class.getClassLoader()));
            return instance;
        }

        public ColorthemeData[] newArray(int size) {
            return (new ColorthemeData[size]);
        }

    };

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Integer getBaseSwatchIndex() {
        return baseSwatchIndex;
    }

    public void setBaseSwatchIndex(Integer baseSwatchIndex) {
        this.baseSwatchIndex = baseSwatchIndex;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<List<Swatch>> getSwatches() {
        return swatches == null ? swatches = new ArrayList<>()  : swatches;
    }

    public void setSwatches(List<List<Swatch>> swatches) {
        this.swatches = swatches;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mood);
        dest.writeValue(baseSwatchIndex);
        dest.writeValue(rule);
        dest.writeList(swatches);
        dest.writeList(tags);
    }

    public int describeContents() {
        return 0;
    }

}
