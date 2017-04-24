package cf.dashika.pipetteworld;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;
import com.androidnetworking.AndroidNetworking;
import com.hwangjr.rxbus.Bus;

import cf.dashika.pipetteworld.Constant.Adobe.Keys;
import cf.dashika.pipetteworld.DI.AppComponent;
import cf.dashika.pipetteworld.DI.DaggerAppComponent;
import cf.dashika.pipetteworld.DI.PresenterModule;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by programer on 13.04.17.
 */

public class ApplicationPipetteWorld extends Application implements IAdobeAuthClientCredentials {

    private static final String CREATIVE_SDK_CLIENT_ID = BuildConfig.CSDK_CLIENT_ID;
    private static final String CREATIVE_SDK_CLIENT_SECRET = BuildConfig.CSDK_CLIENT_SECRET;
    private static final String CREATIVE_SDK_REDIRECT_URI = BuildConfig.CSDK_REDIRECT_URI;
    private static final String[] CREATIVE_SDK_SCOPES = Keys.CSDK_SCOPES;


    private static ApplicationPipetteWorld instance;

    public static ApplicationPipetteWorld get() {
        return instance;
    }

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = buildComponent();
        ActiveAndroid.initialize(this);

        AndroidNetworking.initialize(getApplicationContext());
        bus = new RxBus();
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .presenterModule(new PresenterModule(this))
                .build();
    }

    @Override
    public String getClientID() {
        return CREATIVE_SDK_CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CREATIVE_SDK_CLIENT_SECRET;
    }

    @Override
    public String[] getAdditionalScopesList() {
        return CREATIVE_SDK_SCOPES;
    }

    @Override
    public String getRedirectURI() {
        return CREATIVE_SDK_REDIRECT_URI;
    }

    private RxBus bus;


    public RxBus bus() {
        return bus;
    }

    public static final class RxBus {
        private static Bus sBus;

        static synchronized Bus get() {
            if (sBus == null) {
                sBus = new Bus();
            }
            return sBus;
        }

        private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

        public void send(Object o) {
            bus.onNext(o);
        }

        public Observable<Object> toObserverable() {
            return bus;
        }

        public boolean hasObservers() {
            return bus.hasObservers();
        }
    }
}
