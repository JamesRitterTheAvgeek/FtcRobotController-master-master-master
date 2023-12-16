package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;


@Autonomous(name = "Blue Auto")


public class BlueAuto extends LinearOpMode {
    public boolean right=false;
    public boolean middle=false;
    public boolean left=false;
    double cX = 0;
    double cY = 0;
    double width = 0;
    private DcMotor MotorFrontRight;
    private DcMotor MotorFrontLeft;
    private DcMotor MotorBackRight;
    private DcMotor MotorBackLeft;
    private DcMotor UpsiesMotor;
    //slides
    private DcMotor liftLeft;
    private DcMotor liftRight;
    private DcMotor spoolLift;
    //claw
    private Servo pushBot;
    private Servo armServo;
    private Servo rotateServo;
    //plen
    private Servo planeLaunch;


    private OpenCvCamera controlHubCam;  // Use OpenCvCamera class from FTC SDK
    private static final int CAMERA_WIDTH = 640; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 360; // height of wanted camera resolution


    // Calculate the distance using the formula
    public static final double objectWidthInRealWorldUnits = 3.75;  // Replace with the actual width of the object in real-world units
    public static final double focalLength = 728;  // Replace with the focal length of the camera in pixels
    public Servo drop;


    @Override
    public void runOpMode() {
        MotorFrontLeft = hardwareMap.dcMotor.get("leftFront");
        MotorFrontRight = hardwareMap.dcMotor.get("rightFront");
        MotorBackLeft = hardwareMap.dcMotor.get("leftRear");
        MotorBackRight = hardwareMap.dcMotor.get("rightRear");
        drop = hardwareMap.servo.get("dropServo");
        armServo = hardwareMap.servo.get("clawServo");


        MotorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);


        //liftLeft = hardwareMap.dcMotor.get("liftLeft");
        //  liftRight = hardwareMap.dcMotor.get("liftRight");""
//declare arm servo
        int check=0;
        initOpenCV();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);


        MotorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset the motor encoder
        MotorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Turn the motor back on when we are done
        int bRposition = MotorBackRight.getCurrentPosition();
        MotorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset the motor encoder
        MotorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Turn the motor back on when we are done
        int bLposition = MotorBackRight.getCurrentPosition();
        MotorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset the motor encoder
        MotorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Turn the motor back on when we are done
        int fRposition = MotorBackRight.getCurrentPosition();
        MotorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset the motor encoder
        MotorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Turn the motor back on when we are done
        int fLposition = MotorBackRight.getCurrentPosition();
        waitForStart();


        while (opModeIsActive()) {
            armServo.setPosition(0.1);


            bRposition = MotorBackRight.getCurrentPosition();
            bLposition = MotorBackLeft.getCurrentPosition();
            fRposition = MotorFrontRight.getCurrentPosition();
            fLposition=MotorFrontLeft.getCurrentPosition();
            // Show the position of the motor on telemetry


            telemetry.addData("BR Position",bRposition);
            telemetry.addData("BL Position",bLposition);
            telemetry.addData("FR Position",fRposition);
            telemetry.addData("FL Position",fLposition);
            telemetry.update();
            ElapsedTime runtime2 = new ElapsedTime();
            telemetry.addData("Coordinate", "(" + (int) cX + ", " + (int) cY + ")");
            telemetry.addData("Distance in Inch", (getDistance(width)));
            telemetry.update();
            ElapsedTime elapsedTime = new ElapsedTime();
            while(elapsedTime.seconds()<2){


            }
            while (true) {
                if (cX > 250 && cX < 350) {
                    telemetry.addData("Location", "Middle");
                    while (elapsedTime.seconds() < 8) {


                    }
                    while (true){
                        telemetry.addData("location","middle");


                        fLposition=MotorFrontLeft.getCurrentPosition();
                        fRposition=MotorFrontRight.getCurrentPosition();
                        bRposition=MotorBackRight.getCurrentPosition();
                        bLposition=MotorBackLeft.getCurrentPosition();
                        telemetry.addData("BR Position",bRposition);
                        telemetry.addData("BL Position",bLposition);
                        telemetry.addData("FR Position",fRposition);
                        telemetry.addData("FL Position",fLposition);
                        telemetry.update();
                        if(fRposition*-1>=856){


                            ElapsedTime elapsedTime12= new ElapsedTime();
                            MotorFrontLeft.setPower(0);
                            MotorFrontRight.setPower(0);
                            MotorBackLeft.setPower(0);
                            MotorBackRight.setPower(0);
                            armServo.setPosition(0.9);
                            while (elapsedTime12.seconds()<5){
                                telemetry.addData("Location","middle");
                            }




                            telemetry.addData("BR Position",bRposition);
                            telemetry.addData("BL Position",bLposition);
                            telemetry.addData("FR Position",fRposition);
                            telemetry.addData("FL Position",fLposition);
                            telemetry.update();
                            break;
                        }else{


                            MotorFrontLeft.setPower(-0.8);
                            MotorFrontRight.setPower(-1);
                            MotorBackLeft.setPower(-0.8);
                            MotorBackRight.setPower(-1);
                        }
                    }


                    controlHubCam.stopStreaming();


                    middle = true;
                    left = false;
                    right = false;
                    break;


                } else if (cX > 370) {
                    telemetry.addData("Location", "Right");
                    while (elapsedTime.seconds() < 8) {


                    }
                    while (true){
                        telemetry.addData("location","Right");
                        fLposition=MotorFrontLeft.getCurrentPosition();
                        fRposition=MotorFrontRight.getCurrentPosition();
                        bRposition=MotorBackRight.getCurrentPosition();
                        bLposition=MotorBackLeft.getCurrentPosition();
                        telemetry.addData("BR Position",bRposition);
                        telemetry.addData("BL Position",bLposition);
                        telemetry.addData("FR Position",fRposition);
                        telemetry.addData("FL Position",fLposition);
                        telemetry.update();
                        if(fRposition*-1>=426){
                            ElapsedTime elapsedTime12= new ElapsedTime();
                            MotorFrontLeft.setPower(0);
                            MotorFrontRight.setPower(0);
                            MotorBackLeft.setPower(0);
                            MotorBackRight.setPower(0);


                            armServo.setPosition(0.9);




                            while (elapsedTime12.seconds()<5){
                                telemetry.addData("location","right");
                            }


                            telemetry.addData("BR Position",bRposition);
                            telemetry.addData("BL Position",bLposition);
                            telemetry.addData("FR Position",fRposition);
                            telemetry.addData("FL Position",fLposition);
                            telemetry.update();
                            break;
                        }else{


                            MotorFrontLeft.setPower(-1);
                            MotorFrontRight.setPower(-0.6);
                            MotorBackLeft.setPower(-1);
                            MotorBackRight.setPower(-0.6);
                        }
                    }
                    right = true;
                    left = false;
                    middle = false;
                    controlHubCam.stopStreaming();
                    break;


                } else {
                    telemetry.addData("Location", "Left");
                    while (elapsedTime.seconds() < 8) {


                    }
                    while (true){
                        telemetry.addData("location","Left");
                        fLposition=MotorFrontLeft.getCurrentPosition();
                        fRposition=MotorFrontRight.getCurrentPosition();
                        bRposition=MotorBackRight.getCurrentPosition();
                        bLposition=MotorBackLeft.getCurrentPosition();
                        telemetry.addData("BR Position",bRposition);
                        telemetry.addData("BL Position",bLposition);
                        telemetry.addData("FR Position",fRposition);
                        telemetry.addData("FL Position",fLposition);
                        telemetry.update();
                        if(fRposition*-1>=1096){
                            ElapsedTime elapsedTime12= new ElapsedTime();
                            MotorFrontLeft.setPower(0);
                            MotorFrontRight.setPower(0);
                            MotorBackLeft.setPower(0);
                            MotorBackRight.setPower(0);


                            armServo.setPosition(0.9);




                            while (elapsedTime12.seconds()<5){
                                telemetry.addData("location","left");
                            }


                            telemetry.addData("BR Position",bRposition);
                            telemetry.addData("BL Position",bLposition);
                            telemetry.addData("FR Position",fRposition);
                            telemetry.addData("FL Position",fLposition);
                            telemetry.update();
                            break;
                        }else{


                            MotorFrontLeft.setPower(-0.5);
                            MotorFrontRight.setPower(-1);
                            MotorBackLeft.setPower(-0.5);
                            MotorBackRight.setPower(-1);
                        }
                    }
                    right = false;
                    left = true;
                    middle = false;
                    controlHubCam.stopStreaming();
                    break;


                }
            }












            fRposition=0;
            fLposition=0;
            bRposition=0;
            bLposition=0;


            terminateOpModeNow();


            // The OpenCV pipeline automatically processes frames and handles detection
        }


        // Release resources
    }


    private void initOpenCV() {


        // Create an instance of the camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());


        // Use OpenCvCameraFactory class from FTC SDK to create camera instance
        controlHubCam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);


        controlHubCam.setPipeline(new YellowBlobDetectionPipeline());


        controlHubCam.openCameraDevice();
        controlHubCam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
    }


    class YellowBlobDetectionPipeline extends OpenCvPipeline {
        @Override
        public Mat processFrame(Mat input) {
            // Preprocess the frame to detect yellow regions
            Mat yellowMask = preprocessFrame(input);


            // Find contours of the detected yellow regions
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(yellowMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


            // Find the largest yellow contour (blob)
            MatOfPoint largestContour = findLargestContour(contours);


            if (largestContour != null) {
                // Draw a red outline around the largest detected object
                Imgproc.drawContours(input, contours, contours.indexOf(largestContour), new Scalar(255, 0, 0), 2);
                // Calculate the width of the bounding box
                width = calculateWidth(largestContour);


                // Display the width next to the label
                String widthLabel = "Width: " + (int) width + " pixels";
                Imgproc.putText(input, widthLabel, new Point(cX + 10, cY + 20), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                //Display the Distance
                String distanceLabel = "Distance: " + String.format("%.2f", getDistance(width)) + " inches";
                Imgproc.putText(input, distanceLabel, new Point(cX + 10, cY + 60), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                // Calculate the centroid of the largest contour
                Moments moments = Imgproc.moments(largestContour);
                cX = moments.get_m10() / moments.get_m00();
                cY = moments.get_m01() / moments.get_m00();


                // Draw a dot at the centroid
                String label = "(" + (int) cX + ", " + (int) cY + ")";
                Imgproc.putText(input, label, new Point(cX + 10, cY), Imgproc.FONT_HERSHEY_COMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                Imgproc.circle(input, new Point(cX, cY), 5, new Scalar(0, 255, 0), -1);


            }


            return input;
        }


        private Mat preprocessFrame(Mat frame) {
            Mat hsvFrame = new Mat();
            Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV);


            Scalar lowerYellow = new Scalar(0,50,50);
            Scalar upperYellow = new Scalar(30,255,255);




            Mat yellowMask = new Mat();
            Core.inRange(hsvFrame, lowerYellow, upperYellow, yellowMask);


            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_CLOSE, kernel);


            return yellowMask;
        }


        private MatOfPoint findLargestContour(List<MatOfPoint> contours) {
            double maxArea = 0;
            MatOfPoint largestContour = null;


            for (MatOfPoint contour : contours) {
                double area = Imgproc.contourArea(contour);
                if (area > maxArea) {
                    maxArea = area;
                    largestContour = contour;
                }
            }


            return largestContour;
        }
        public String place(){
            if (left=true){
                return "left";
            }else if (right=true){
                return "right";


            }else{
                return "middle";
            }
        }
        private double calculateWidth(MatOfPoint contour) {
            Rect boundingRect = Imgproc.boundingRect(contour);
            return boundingRect.width;
        }


    }
    private static double getDistance(double width){
        double distance = (objectWidthInRealWorldUnits * focalLength) / width;
        return distance;
    }




}
