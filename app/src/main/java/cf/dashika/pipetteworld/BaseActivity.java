package cf.dashika.pipetteworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.adobe.creativesdk.foundation.auth.AdobeAuthSessionHelper;
import com.adobe.creativesdk.foundation.auth.AdobeAuthSessionLauncher;
import com.adobe.creativesdk.foundation.auth.AdobeUXAuthManager;
import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthIdentityManagementService;
import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthManager;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import cf.dashika.pipetteworld.Model.Adobe.Elements;
import cf.dashika.pipetteworld.Model.Adobe.Libraries;
import cf.dashika.pipetteworld.Model.Adobe.Library;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by programer on 14.04.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Menu menu;
    static final int REQ_CODE_CSDK_USER_AUTH = 1001;
    private AdobeUXAuthManager mUXAuthManager = AdobeUXAuthManager.getSharedAuthManager();
    private AdobeAuthSessionHelper mAuthSessionHelper;
    boolean login = false;

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        mAuthSessionHelper = new AdobeAuthSessionHelper(mStatusCallback);
        mAuthSessionHelper.onCreate(savedInstanceState);
    }

    private AdobeAuthSessionHelper.IAdobeAuthStatusCallback mStatusCallback;
    {
        mStatusCallback = (adobeAuthStatus, e) -> {
            if (!mUXAuthManager.isAuthenticated()) {
                login = false;
                updateMenuTitles();
            } else {

                //"User successfully logged in!"
                login = true;
                updateMenuTitles();
            }
        };
    }

    AdobeAuthSessionLauncher authSessionLauncher;

    private void login() {
        authSessionLauncher = new AdobeAuthSessionLauncher.Builder()
                .withActivity(this)
                .withRequestCode(REQ_CODE_CSDK_USER_AUTH)
                .build();
        mUXAuthManager.login(authSessionLauncher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAuthSessionHelper != null)
            mAuthSessionHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthSessionHelper != null)
            mAuthSessionHelper.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuthSessionHelper != null)
            mAuthSessionHelper.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthSessionHelper != null)
            mAuthSessionHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAuthSessionHelper != null)
            mAuthSessionHelper.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAuthSessionHelper.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQ_CODE_CSDK_USER_AUTH:
                    //"User successfully logged in!"
                    login = true;
                    updateMenuTitles();
                    finish();
                    startActivity(new Intent(this,CameraActivity.class));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        updateMenuTitles();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            MenuItem menuItem = menu.findItem(R.id.action_logout);
            if(menuItem.getTitle().equals(getResources().getString(R.string.action_logout))) {
                AdobeAuthManager.sharedAuthManager().logout();
                login = false;
                updateMenuTitles();
            }
            else {
                login();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateMenuTitles() {
        if(menu==null) return;
        MenuItem bedMenuItem = menu.findItem(R.id.action_logout);
        if (login) {
            bedMenuItem.setTitle(getResources().getString(R.string.action_logout));
        } else {
            bedMenuItem.setTitle(getResources().getString(R.string.action_login));
        }
    }

}
