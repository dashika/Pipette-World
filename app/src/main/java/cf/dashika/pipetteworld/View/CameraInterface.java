package cf.dashika.pipetteworld.View;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;

import java.io.IOException;


public class CameraInterface {

    private Camera mCamera;
    private static CameraInterface mCameraInterface;

     public interface CamOpenOverCallback {
        void cameraHasOpened();
    }

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    public void doOpenCamera(CamOpenOverCallback callback) throws Exception {
        if(!safeCameraOpen())
             throw new Exception("Camera not open");
        callback.cameraHasOpened();
    }

    private boolean safeCameraOpen() {
        boolean qOpened = false;

        try {
            doStopCamera();
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            qOpened = (mCamera != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return qOpened;
    }

    public void doStartPreview(SurfaceHolder holder,float previewRate) {

        if (mCamera != null) {
            Camera.Parameters mParams = mCamera.getParameters();

            Size pictureSize = CameraUtil.getInstance().getPropPictureSize(
                    mParams.getSupportedPictureSizes(), previewRate);
            mParams.setPictureSize(pictureSize.width, pictureSize.height);
            Size previewSize = CameraUtil.getInstance().getPropPreviewSize(
                    mParams.getSupportedPreviewSizes(), previewRate);
            mParams.setPreviewSize(previewSize.width, previewSize.height);

            mCamera.setDisplayOrientation(90);
            mCamera.setParameters(mParams);

            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPreviewCallback(Camera.PreviewCallback callback) {
        if (null == mCamera) {
            return;
        }
        mCamera.setPreviewCallback(callback);
    }

     void doStopCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

}