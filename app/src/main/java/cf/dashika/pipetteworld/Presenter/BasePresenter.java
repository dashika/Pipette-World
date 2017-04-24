package cf.dashika.pipetteworld.Presenter;

import javax.inject.Inject;

import cf.dashika.pipetteworld.ApplicationPipetteWorld;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        ApplicationPipetteWorld.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

}
