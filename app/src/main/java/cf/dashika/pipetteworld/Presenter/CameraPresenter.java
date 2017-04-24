package cf.dashika.pipetteworld.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthManager;

import org.json.JSONException;

import java.util.List;

import javax.inject.Inject;

import cf.dashika.pipetteworld.ApplicationPipetteWorld;
import cf.dashika.pipetteworld.DisplayUtil;
import cf.dashika.pipetteworld.Model.Adobe.Element;
import cf.dashika.pipetteworld.Model.Adobe.Elements;
import cf.dashika.pipetteworld.Model.Adobe.Libraries;
import cf.dashika.pipetteworld.Model.Adobe.Library;
import cf.dashika.pipetteworld.Model.Adobe.Swatch;
import cf.dashika.pipetteworld.Model.CameraModel;
import cf.dashika.pipetteworld.Model.DB.ElementDB;
import cf.dashika.pipetteworld.Model.DB.ValueDB;
import cf.dashika.pipetteworld.Util.Events;
import cf.dashika.pipetteworld.View.CameraInterface;
import rx.android.schedulers.AndroidSchedulers;

import static cf.dashika.pipetteworld.Util.AdobeSentScheme.addElements;

/**
 * Created by programer on 13.04.17.
 */

public class CameraPresenter extends BasePresenter {

    private static Point p;

    private SurfaceView view;
    private TextView tvColorHEX;
    private LinearLayout layoutHex;
    private ImageView imgHEX;

    @Inject
    CameraModel cameraModel;

    @Inject
    Libraries libraries;

    @Inject
    Context context;

    private static ElementDB elementDB;

    private Library currentLibrary;

    public void onCreate(SurfaceView view, TextView textView, LinearLayout layoutHex, ImageView imageView) {
        ApplicationPipetteWorld.getComponent().inject(this);
        this.view = view;
        this.tvColorHEX = textView;
        this.layoutHex = layoutHex;
        this.imgHEX = imageView;
        p = DisplayUtil.getScreenMetrics(context);
        initViewParams();
        String nameLib = "MyDefault" + libraries.getLibraries().size();
        currentLibrary = libraries.findLibrary(nameLib);
        if (currentLibrary == null) {
            initLibrary(nameLib);
        }
        if (elementDB == null) elementDB = new ElementDB();
    }

    private void initLibrary(String nameLib) {
        currentLibrary = new Library();
        currentLibrary.setName(nameLib);
        currentLibrary.setState("my");
        currentLibrary.setEtag("my");
        libraries.getLibraries().add(currentLibrary);
    }

    private void initViewParams() {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(context);
        params.width = p.x;
        params.height = p.y;
        cameraModel.setPreviewRate(DisplayUtil.getScreenRate(context));
        view.setLayoutParams(params);
        cameraModel.setPixels(new int[params.width * params.height]);
    }

    public void addEventListener() {
        addSubscription(ApplicationPipetteWorld.get().bus().toObserverable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::handlerBus
                ));
    }

    private void handlerBus(Object o) {
        if (o instanceof Events.onClickCamera) {
            Point point = ((Events.onClickCamera) o).getPoint();
            if (cameraModel != null) {
                cameraModel.setX(point.x);
                cameraModel.setY(point.y);
                onPreviewFrame(data, true);
            }
        }
    }

    public void cameraHasOpened(SurfaceHolder holder) {
        CameraInterface.getInstance().doStartPreview(holder, cameraModel.getPreviewRate());
    }

    private byte[] data;
    private int color;

    public void onPreviewFrame(byte[] data, boolean there) {
        if (cameraModel == null || cameraModel.getPixels() == null || data == null) return;
        this.data = data;
        try {
            DisplayUtil.decodeYUV420SP(cameraModel.getPixels(), data, p.x, p.y);
            String a = "#" + Integer.toHexString(cameraModel.getPixels()[cameraModel.getX() + p.y * cameraModel.getY()]);
            if (there) replaceContainer(a);
            try {
                color = Color.parseColor(a);
                imgHEX.setBackgroundColor(color);
                int neg = (0xFFFFFF - color) | 0xFF000000;
                tvColorHEX.setTextColor(neg);
            } catch (IllegalArgumentException ignore) {
            }

            tvColorHEX.setText(a);
        } catch (ArrayIndexOutOfBoundsException ignore) {
            ignore.printStackTrace();
        }
    }

    private void replaceContainer(String a) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(cameraModel.getX(), cameraModel.getY(), 0, 0);

        layoutHex.setLayoutParams(layoutParams);
    }

    public void addHEX() {
        int size = libraries.addColor(currentLibrary.getName(), color);
        if (size == 5 && AdobeAuthManager.sharedAuthManager().isAuthenticated()) {
            Elements elements = currentLibrary.getElements().get(currentLibrary.getElements().size() - 1);
            Element element = elements.getElements().get(elements.getElements().size() - 1);

            //add new element
            try {
                addElements(libraries.getLastIdLib(), element, elements1 -> {
                    Toast.makeText(context, "Palette send to Adobe account successful", Toast.LENGTH_LONG).show();
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            migrateToLocal(size - 1);
            elementDB.save();
            if (size == 5) {
                elementDB = new ElementDB();
            }
        }
    }

    private void migrateToLocal(int lastColor) {
        Elements elements = currentLibrary.getElements().get(currentLibrary.getElements().size() - 1);
        Element element = elements.getElements().get(elements.getElements().size() - 1);

        elementDB.setName(element.getName());
        List<List<Swatch>> colorthemeData = element.getRepresentations().get(element.getRepresentations().size() - 1).getColorthemeData().getSwatches();
        //  for (Swatch swatch : colorthemeData.get(0)) {
        elementDB.save();
        ValueDB valueDB = new ValueDB(elementDB).migrateFromValue(colorthemeData.get(lastColor).get(0).getValue());
        valueDB.save();
        //  }

    }


}
