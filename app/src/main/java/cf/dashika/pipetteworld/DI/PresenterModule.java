package cf.dashika.pipetteworld.DI;

import android.content.Context;

import javax.inject.Singleton;

import cf.dashika.pipetteworld.Model.CameraModel;
import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module

public class PresenterModule {

    @Provides
    @Singleton
    CameraModel cameraModel() {
        return new CameraModel();
    }

    private final Context context;

    public PresenterModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context;
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
