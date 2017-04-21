
package cf.dashika.pipetteworld.Model.Adobe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Library implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("num_elements")
    @Expose
    private Integer numElements;
    @SerializedName("library_scope")
    @Expose
    private String libraryScope;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("renditions")
    @Expose
    private List<Object> renditions = null;

    public List<Elements> getElements() {
        return elements == null ? elements = new ArrayList<>() : elements;
    }

    public void setElements(List<Elements> elements) {
        this.elements = elements;
    }

    private List<Elements> elements = null;

    public final static Parcelable.Creator<Library> CREATOR = new Creator<Library>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Library createFromParcel(Parcel in) {
            Library instance = new Library();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));

            instance.state = ((String) in.readValue((String.class.getClassLoader())));
            instance.numElements = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.libraryScope = ((String) in.readValue((String.class.getClassLoader())));
            instance.etag = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.renditions, (java.lang.Object.class.getClassLoader()));
            in.readList(instance.elements, Elements.class.getClassLoader());
            return instance;
        }

        public Library[] newArray(int size) {
            return (new Library[size]);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getNumElements() {
        return numElements;
    }

    public void setNumElements(Integer numElements) {
        this.numElements = numElements;
    }

    public String getLibraryScope() {
        return libraryScope;
    }

    public void setLibraryScope(String libraryScope) {
        this.libraryScope = libraryScope;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
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
        dest.writeValue(state);
        dest.writeValue(numElements);
        dest.writeValue(libraryScope);
        dest.writeValue(etag);
        dest.writeList(renditions);
        dest.writeList(elements);
    }

    public int describeContents() {
        return 0;
    }

}
