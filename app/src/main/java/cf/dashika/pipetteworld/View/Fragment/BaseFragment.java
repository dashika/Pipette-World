package cf.dashika.pipetteworld.View.Fragment;

import android.support.v4.app.Fragment;

import cf.dashika.pipetteworld.Presenter.Presenter;

public abstract class BaseFragment extends Fragment {

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

}

