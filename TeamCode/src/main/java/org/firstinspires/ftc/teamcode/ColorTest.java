package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorTest extends OpenCvPipeline {

    public enum ParkPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    private Mat coneCrop = new Mat(), red = new Mat(), green = new Mat(), blue = new Mat(), output = new Mat();
    double redavgfin;
    double greenavgfin;
    double blueavgfin;
    double winner;

    private final Scalar
            yellow      = new Scalar(255, 255, 0),
            cyan        = new Scalar(0, 255, 255),
            magenta     = new Scalar(255, 0, 255);

    private volatile ParkPosition position = ParkPosition.CENTER;

    @Override
    public Mat processFrame(Mat input) {

        Rect ConeRectangle = new Rect(300, 70, 40, 60);



        coneCrop = input.submat(ConeRectangle);

        Core.extractChannel(coneCrop, red, 0);


        Scalar redavg   = Core.mean(red);
        Scalar greenavg = Core.mean(green);
        Scalar blueavg  = Core.mean(blue);

        redavgfin = redavg.val[0];
        greenavgfin = greenavg.val[0];
        blueavgfin = blueavg.val[0];

        winner = Math.min(redavgfin, Math.min(greenavgfin, blueavgfin));

        if (redavgfin == winner || blueavgfin==winner) {
            position = ParkPosition.CENTER;
            input.copyTo(output);
            Imgproc.rectangle(output, ConeRectangle, cyan, 2);
        } else {
            position=null;
        }


        return(output);
    }

    public ParkPosition getPosition() {
        return position;
    }

}