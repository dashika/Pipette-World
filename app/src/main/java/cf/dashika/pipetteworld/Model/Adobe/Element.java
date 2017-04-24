
package cf.dashika.pipetteworld.Model.Adobe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Element implements Parcelable {

    public boolean isItsAdobeObject() {
        return itsAdobeObject;
    }

    public void setItsAdobeObject(boolean itsAdobeObject) {
        this.itsAdobeObject = itsAdobeObject;
    }

    private boolean itsAdobeObject = true;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("element-ref")
    @Expose
    private String elementRef;
    @SerializedName("type")
    @Expose
    private String type = "application/vnd.adobe.element.colortheme+dcx";
    @SerializedName("representations")
    @Expose
    private List<Representation> representations = null;
    @SerializedName("renditions")
    @Expose
    private List<Object> renditions = null;
    public final static Parcelable.Creator<Element> CREATOR = new Creator<Element>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Element createFromParcel(Parcel in) {
            Element instance = new Element();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.elementRef = ((String) in.readValue((String.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));

            in.readList(instance.representations, (Representation.class.getClassLoader()));
            in.readList(instance.renditions, (java.lang.Object.class.getClassLoader()));
            return instance;
        }

        public Element[] newArray(int size) {
            return (new Element[size]);
        }

    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElementRef() {
        return elementRef;
    }

    public void setElementRef(String elementRef) {
        this.elementRef = elementRef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Representation> getRepresentations() {
        return representations == null ? representations = new ArrayList<>() : representations;
    }

    public void setRepresentations(List<Representation> representations) {
        this.representations = representations;
    }

    public List<Object> getRenditions() {
        return renditions;
    }

    public void setRenditions(List<Object> renditions) {
        this.renditions = renditions;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(elementRef);
        dest.writeValue(type);
        dest.writeList(representations);
        dest.writeList(renditions);
    }

    public int describeContents() {
        return 0;
    }

}
