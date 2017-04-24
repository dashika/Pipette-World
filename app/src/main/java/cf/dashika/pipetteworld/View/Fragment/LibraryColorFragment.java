package cf.dashika.pipetteworld.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cf.dashika.pipetteworld.ApplicationPipetteWorld;
import cf.dashika.pipetteworld.Model.Adobe.Libraries;
import cf.dashika.pipetteworld.Presenter.BasePresenter;
import cf.dashika.pipetteworld.Presenter.LibraryColorPresenter;
import cf.dashika.pipetteworld.R;
import cf.dashika.pipetteworld.View.Adapter.LibraryRecyclerViewAdapter;

public class LibraryColorFragment extends BaseFragment {

    @Inject
    LibraryColorPresenter libraryColorPresenter;

    public LibraryColorFragment() {
    }


    @Override
    protected BasePresenter getPresenter() {
        ApplicationPipetteWorld.getComponent().inject(this);
        return libraryColorPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_librarycolor_list, container, false);

        ApplicationPipetteWorld.getComponent().inject(this);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            libraryColorPresenter.onCreate(recyclerView);
        }
        return view;
    }


}
