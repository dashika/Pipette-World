package cf.dashika.pipetteworld.View.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cf.dashika.pipetteworld.ApplicationPipetteWorld;
import cf.dashika.pipetteworld.Model.CameraModel;
import cf.dashika.pipetteworld.Presenter.BasePresenter;
import cf.dashika.pipetteworld.Presenter.CameraPresenter;
import cf.dashika.pipetteworld.R;
import cf.dashika.pipetteworld.View.CameraInterface;
import cf.dashika.pipetteworld.View.CameraSurfaceView;

import static cf.dashika.pipetteworld.CameraActivity.MY_PERMISSIONS_REQUEST_CAMERA;
import static cf.dashika.pipetteworld.Constant.CameraConstant.CAMERA_MODEL;

public class CameraFragment extends BaseFragment implements CameraInterface.CamOpenOverCallback, Camera.PreviewCallback {

    @Inject
    CameraPresenter cameraPresenter;

    @BindView(R.id.camera_surfaceview)
    CameraSurfaceView surfaceView;
    @BindView(R.id.tvColorHEX)
    TextView tvColorHEX;
    @BindView(R.id.layoutHEX)
    LinearLayout layoutHex;
    @BindView(R.id.imgColorHEX)
    ImageView imgHEX;

    @Override
    protected BasePresenter getPresenter() {
        ApplicationPipetteWorld.getComponent().inject(this);
        return cameraPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        ButterKnife.bind(this, view);
        ApplicationPipetteWorld.getComponent().inject(this);


        return view;
    }

    public void onResume()
    {
        super.onResume();
        cameraPresenter.onCreate(surfaceView, tvColorHEX, layoutHex, imgHEX);

        openCam();
        cameraPresenter.addEventListener();
    }

    public void openCam()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(getActivity(), "request permission", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "PERMISSION_ALREADY_GRANTED", Toast.LENGTH_SHORT).show();

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    CameraInterface.getInstance().doOpenCamera(CameraFragment.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            })
                    .start();

        }
    }

    @Override
    public void cameraHasOpened() {
        SurfaceHolder holder = surfaceView.getSurfaceHolder();
        CameraInterface.getInstance().setPreviewCallback(this);
        cameraPresenter.cameraHasOpened(holder);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        getActivity().runOnUiThread(() -> cameraPresenter.onPreviewFrame(data,false));
    }

    @OnClick(R.id.btnAddHEX)
    public void addHEX(View view) {
        cameraPresenter.addHEX();
    }
}
