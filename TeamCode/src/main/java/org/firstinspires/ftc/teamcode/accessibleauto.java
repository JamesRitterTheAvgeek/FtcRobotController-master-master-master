package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class accessibleauto extends LinearOpMode {
    private DcMotor MotorFrontLeft = null;
    private DcMotor MotorFrontRight = null;
    private DcMotor MotorBackLeft = null;
    private DcMotor MotorBackRight = null;
    private DcMotor Liftleft = null;
    //arm
    private Servo armServo = null;
    private Servo rotateServo = null;
    private static final double CLAW_UP = -1;

    private static final double CLAW_DOWN = 1;

    private static final double CLAW_NEUTURAL = 0;

    private static final double ARM_RETRACTED_POSITION = 0.1;
    private static final double ARM_EXTENDED_POSITION = 0.8;
    double returnvalue = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        MotorFrontLeft = hardwareMap.get(DcMotor.class, "fldrive");
        MotorFrontRight = hardwareMap.get(DcMotor.class, "frdrive");
        MotorBackLeft = hardwareMap.get(DcMotor.class, "bldrive");
        MotorBackRight = hardwareMap.get(DcMotor.class, "brdrive");

        MotorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        MotorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        if (opModeIsActive()) {


            if (returnvalue == 1) {
                ElapsedTime runtime2 = new ElapsedTime();
                while(runtime2.seconds()<0.75){
                    MotorFrontLeft.setPower(.5);
                    MotorFrontRight.setPower(.5);
                    MotorBackLeft.setPower(.5);
                    MotorBackRight.setPower(.5);
                }
                setDriveStop();
            }
        }


    }

    private void setDriveStop() {
        MotorFrontLeft.setPower(0);
        MotorFrontRight.setPower(0);
        MotorBackLeft.setPower(0);
        MotorBackRight.setPower(0);
    }

}
