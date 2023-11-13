package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
@Autonomous(name = "Six_Chicken_Tenders_right", group = "Cyber_Eagles")
public class ChanceAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();


    private Object parkingposition;
     ColorTest test= new ColorTest();

    @Override
    public void runOpMode()  {

        telemetry.addData("Status", "Initializing");
        telemetry.update();

        //add servo init
        test = new ColorTest();

        Rect ConeRectangle = new Rect(300, 70, 40, 60);



       // robot.frontcamera.setPipeline(ChanceAuto);

       /* robot.frontcamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                robot.frontcamera.startStreaming(640,480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });

        */
        waitForStart();
        sleep(2000);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        telemetry.addData("Park:", test.getPosition());
        parkingposition = test.getPosition();
        sleep(1000);
        telemetry.update();





//        if (parkingposition == SleeveDetectionBetter.ParkPosition.LEFT) {
//            drive.rightRear.setPower(-.5);
//            drive.rightFront.setPower(-.5);
//            drive.leftFront.setPower(-.5);
//            drive.leftRear.setPower(-.5);
//        } else if(parkingposition == SleeveDetectionBetter.ParkPosition.RIGHT) {
//            drive.rightRear.setPower(.5);
//            drive.rightFront.setPower(.5);
//            drive.leftFront.setPower(.5);
//            drive.leftRear.setPower(.5);
//        }
//        sleep(1200);
//        drive.rightRear.setPower(0);
//        drive.rightFront.setPower(0);
//        drive.leftFront.setPower(0);
//        drive.leftRear.setPower(0);
    }


}