package cf.dashika.pipetteworld.DI;

import android.content.Context;

import javax.inject.Singleton;

import cf.dashika.pipetteworld.Presenter.BasePresenter;
import cf.dashika.pipetteworld.Presenter.CameraPresenter;
import cf.dashika.pipetteworld.Presenter.LibraryColorPresenter;
import cf.dashika.pipetteworld.View.Fragment.CameraFragment;
import cf.dashika.pipetteworld.View.Fragment.LibraryColorFragment;
import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    Context context();

    void inject(CameraPresenter cameraPresenter);

    void inject(BasePresenter basePresenter);

    void inject(CameraFragment cameraFragment);

    void inject(LibraryColorFragment libraryColorFragment);

    void inject(LibraryColorPresenter libraryColorPresenter);
}
