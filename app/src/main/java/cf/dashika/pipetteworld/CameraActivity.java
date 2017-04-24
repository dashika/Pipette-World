package cf.dashika.pipetteworld;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import cf.dashika.pipetteworld.View.Fragment.CameraFragment;
import cf.dashika.pipetteworld.View.Fragment.LibraryColorFragment;

public class CameraActivity extends BaseActivity {

    public final static int MY_PERMISSIONS_REQUEST_CAMERA = 123;

    private final String TAG_CAMERA = "camera";
    private final String TAG_LIB_LIST = "library";

    private FragmentManager fragmentManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_camera);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG_CAMERA);
        if (fragment == null) replaceFragment(new CameraFragment(), false, TAG_CAMERA);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment, tag);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do something
                } else{
                    //do some other thing
                }
                break;
        }
    }

    public void showLibraryColor(View view) {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_LIB_LIST);
        if (fragment == null) replaceFragment(new LibraryColorFragment(), true, TAG_LIB_LIST);
    }
}
