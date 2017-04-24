package cf.dashika.pipetteworld.Util;

import android.graphics.Point;

/**
 * Created by programer on 13.04.17.
 */

public class Events {
    private Events(){}

    public static class onClickCamera {
        public Point getPoint() {
            return point;
        }

        final Point point;

        public onClickCamera(Point point) {
            this.point = point;
        }
    }
}
