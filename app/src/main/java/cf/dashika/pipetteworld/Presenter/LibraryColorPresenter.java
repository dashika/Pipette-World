package cf.dashika.pipetteworld.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthIdentityManagementService;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;

import cf.dashika.pipetteworld.ApplicationPipetteWorld;
import cf.dashika.pipetteworld.BuildConfig;
import cf.dashika.pipetteworld.Model.Adobe.Element;
import cf.dashika.pipetteworld.Model.Adobe.Elements;
import cf.dashika.pipetteworld.Model.Adobe.Libraries;
import cf.dashika.pipetteworld.Model.Adobe.Library;
import cf.dashika.pipetteworld.View.Adapter.LibraryRecyclerViewAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by programer on 18.04.17.
 */

public class LibraryColorPresenter extends BasePresenter {

    @Inject
    Libraries libraries;

    @Inject
    Context context;

    private List<Element> mValues;

    private static boolean start = false;

    private LibraryRecyclerViewAdapter libraryRecyclerViewAdapter;

    public void onCreate(RecyclerView recyclerView) {
        ApplicationPipetteWorld.getComponent().inject(this);
        mValues = libraries.getPallete();
        if (!start && countNotMy() == 0)
            getLibraries(elements -> {
                start = false;
                mValues.addAll(elements.getElements());
               // libraryRecyclerViewAdapter.animateTo(mValues);
                libraryRecyclerViewAdapter.notifyDataSetChanged();
            });

        libraryRecyclerViewAdapter = new LibraryRecyclerViewAdapter(mValues, context);
        recyclerView.setAdapter(libraryRecyclerViewAdapter);
    }

    private int countNotMy() {
        int count = 0;
        for (Element element : mValues) {
            if (element.isItsAdobeObject()) count++;
        }
        return count;
    }

    private synchronized void getLibraries(Callback callback) {
        start = true;
        Rx2AndroidNetworking.get(BuildConfig.BASE_URL)
                .addHeaders("x-api-key", BuildConfig.CSDK_CLIENT_ID)
                .addHeaders("Authorization", "Bearer " + AdobeAuthIdentityManagementService.getSharedInstance().getAccessToken())
                .build()
                .getObjectObservable(Libraries.class)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<Libraries>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Libraries librariess) {
                        for (Library lib : librariess.getLibraries()) {
                            getElements(lib.getId(), elements -> {
                                lib.getElements().add(elements);
                                libraries.getLibraries().add(lib);
                                callback.getElements(elements);
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        //saveinstance
                        Toast.makeText(context, "Successful load palette from Adobe", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public interface Callback {
        void getElements(Elements elements);
    }

    private void getElements(String id, Callback callback) {
        Rx2AndroidNetworking.get(BuildConfig.BASE_URL + "{id_lib}")
                .addPathParameter("id_lib", id)
                .addHeaders("x-api-key", BuildConfig.CSDK_CLIENT_ID)
                .addHeaders("Authorization", "Bearer " + AdobeAuthIdentityManagementService.getSharedInstance().getAccessToken())
                .build()
                .getObjectObservable(Elements.class)
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<Elements>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Elements elements) {
                        callback.getElements(elements);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        //saveinstance
                    }
                });
    }

}
