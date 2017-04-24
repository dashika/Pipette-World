package cf.dashika.pipetteworld.Util;

import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthIdentityManagementService;
import com.google.gson.Gson;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import cf.dashika.pipetteworld.BuildConfig;
import cf.dashika.pipetteworld.Model.Adobe.Element;
import cf.dashika.pipetteworld.Model.Adobe.Elements;
import cf.dashika.pipetteworld.Presenter.LibraryColorPresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dashika on 21/04/17.
 */

public class AdobeSentScheme {
    public interface Callback {
        void getElement(Element element);
    }
   public synchronized static void addElements(String libId, Element element, Callback callback) throws JSONException {
        Rx2AndroidNetworking.post(BuildConfig.BASE_URL + "{id_lib}/elements")
                .addPathParameter("id_lib", libId)
                .addJSONObjectBody(new JSONObject(new Gson().toJson(element)))
                .addHeaders("x-api-key", BuildConfig.CSDK_CLIENT_ID)
                .addHeaders("Authorization", "Bearer " + AdobeAuthIdentityManagementService.getSharedInstance().getAccessToken())
                .build()
                .getObjectObservable(Element.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Element>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Element elements) {
                         callback.getElement(elements);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onComplete() {
                        //saveinstance
                    }
                });
    }

}
