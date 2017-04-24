
package cf.dashika.pipetteworld.Model.Adobe;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthIdentityManagementService;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cf.dashika.pipetteworld.BuildConfig;
import cf.dashika.pipetteworld.Presenter.LibraryColorPresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Libraries implements Parcelable {

    @SerializedName("libraries")
    @Expose
    private List<Library> libraries = null;
    public final static Parcelable.Creator<Libraries> CREATOR = new Creator<Libraries>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Libraries createFromParcel(Parcel in) {
            Libraries instance = new Libraries();
            in.readList(instance.libraries, (Library.class.getClassLoader()));
            return instance;
        }

        public Libraries[] newArray(int size) {
            return (new Libraries[size]);
        }

    };

    public List<Library> getLibraries() {
        return libraries == null ? libraries = new ArrayList<>() : libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(libraries);
    }

    public int describeContents() {
        return 0;
    }

    public synchronized List<Element> getPallete() {
        List<Element> mValues = new ArrayList<>();
        for (Library library : this.getLibraries()) {
            for (Elements elements : library.getElements()) {
                mValues.addAll(elements.getElements());
            }
        }
        return mValues;
    }

    public synchronized int addColor(String name, int color) {
        for (Library library : this.getLibraries()) {
            if (library.getName().equals(name)) {
                if (library.getElements().isEmpty()) {
                    Elements elements = new Elements();
                    elements.setEtag("my");
                    elements.setName("my");
                    elements.setState("my");
                    library.getElements().add(elements);
                }
                Elements elements = library.getElements().get(library.getElements().size() - 1);
                if (elements.getElements().isEmpty()) {
                    elements.getElements().add(addElement(elements.getElements().size()));
                }
                List<Representation> representations = elements.getElements().get(elements.getElements().size() - 1).getRepresentations();
                if (representations.isEmpty()) {
                    representations.add(new Representation());
                }
                ColorthemeData colorthemeData = representations.get(representations.size() - 1).getColorthemeData();
                List<List<Swatch>> lists = colorthemeData.getSwatches();

                if (lists.size() == 5) {

                    elements.getElements().add(addElement(elements.getElements().size()));

                    representations = elements.getElements().get(elements.getElements().size() - 1).getRepresentations();
                    if (representations.isEmpty()) {
                        representations.add(new Representation());
                    }
                    colorthemeData = representations.get(representations.size() - 1).getColorthemeData();
                    lists = colorthemeData.getSwatches();
                }

                Swatch swatch = new Swatch();
                Value value = new Value();
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                value.setR(red);
                value.setB(blue);
                value.setG(green);
                swatch.setValue(value);
                List<Swatch> swatches = new ArrayList<>();
                swatches.add(swatch);
                lists.add(swatches);

                return lists.size();
            }
        }
        return 0;
    }

    public String getLastIdLib() {
        for (Library library : libraries) {
            if (library.getId() != null) return library.getId();
        }
        return null;
    }




    private Element addElement(int count) {
        Element element = new Element();
        element.setName("my" + count);
        element.setItsAdobeObject(false);
        return element;
    }

    public Library findLibrary(String name) {
        for (Library library : this.getLibraries()) {
            if (library.getName().equals(name)) {
                return library;
            }
        }
        return null;
    }


}
