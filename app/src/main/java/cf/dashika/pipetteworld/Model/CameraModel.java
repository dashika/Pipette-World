package cf.dashika.pipetteworld.Model;

import android.os.Parcel;
import android.os.Parcelable;

import dagger.Module;
import paperparcel.PaperParcel;

/**
 * Created by programer on 13.04.17.
 */

@PaperParcel
public class CameraModel implements Parcelable {

    public float getPreviewRate() {
        return previewRate;
    }

    public void setPreviewRate(float previewRate) {
        this.previewRate = previewRate;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    private float previewRate = -1f;
    private int[] pixels;
    private int X, Y;

    public CameraModel(){}

    public CameraModel(Parcel in) {
        previewRate = in.readFloat();
        pixels = in.createIntArray();
        X = in.readInt();
        Y = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(previewRate);
        dest.writeIntArray(pixels);
        dest.writeInt(X);
        dest.writeInt(Y);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CameraModel> CREATOR = new Creator<CameraModel>() {
        @Override
        public CameraModel createFromParcel(Parcel in) {
            return new CameraModel(in);
        }

        @Override
        public CameraModel[] newArray(int size) {
            return new CameraModel[size];
        }
    };
}
