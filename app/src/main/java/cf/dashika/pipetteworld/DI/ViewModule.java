package cf.dashika.pipetteworld.DI;

import cf.dashika.pipetteworld.Presenter.CameraPresenter;
import cf.dashika.pipetteworld.Presenter.LibraryColorPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    CameraPresenter cameraPresenter() {
        return new CameraPresenter();
    }

    @Provides
    LibraryColorPresenter libraryColorPresenter() {
        return new LibraryColorPresenter();
    }

}
