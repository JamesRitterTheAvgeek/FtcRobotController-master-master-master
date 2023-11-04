package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class accessibleauto extends LinearOpMode {
    private DcMotor MotorFrontLeft;
    private DcMotor MotorFrontRight;
    private DcMotor MotorBackLeft;
    private DcMotor MotorBackRight;
    private DcMotor Liftleft;
    //arm
    private Servo armServo;
    private Servo rotateServo;
    private static final double CLAW_UP = -1;

    private static final double CLAW_DOWN = 1;

    private static final double CLAW_NEUTURAL = 0;

    private static final double ARM_RETRACTED_POSITION = 0.1;
    private static final double ARM_EXTENDED_POSITION = 0.8;
    @Override
    public void runOpMode() throws InterruptedException {
        MotorFrontLeft = hardwareMap.dcMotor.get("MotorFrontLeft");
        MotorFrontRight = hardwareMap.dcMotor.get("MotorFrontRight");
        MotorBackLeft = hardwareMap.dcMotor.get("MotorBackLeft");
        MotorBackRight = hardwareMap.dcMotor.get("MotorBackRight");
        MotorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset the motor encoder
        MotorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset the motor encoder
        MotorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MotorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
// Turn the motor back on when we are done
        double x=0;
        double y=0;
        double leftPosition=0;
        double rightPosition=0;
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Get the current position of the motor
            while (true) {
                 leftPosition= MotorBackLeft.getCurrentPosition();
                 rightPosition=MotorBackRight.getCurrentPosition();
                x=leftPosition;
                y=rightPosition;
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                telemetry.update();
                MotorBackLeft.setPower(-0.8);
                MotorBackRight.setPower(0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                x=leftPosition;
                y=rightPosition;
                MotorFrontLeft.setPower(0.8);
                MotorFrontRight.setPower(-0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                x=leftPosition;
                y=rightPosition;
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                telemetry.update();
                MotorBackLeft.setPower(0.8);
                MotorBackRight.setPower(-0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                x=leftPosition;
                y=rightPosition;
                MotorFrontLeft.setPower(-0.8);
                MotorFrontRight.setPower(0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                x=leftPosition;
                y=rightPosition;
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                telemetry.update();
                MotorBackLeft.setPower(-0.8);
                MotorBackRight.setPower(0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                x=leftPosition;
                y=rightPosition;
                //move fowarard 1 tile
                MotorFrontRight.setPower(0.8);
                MotorFrontLeft.setPower(-0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                sleep(250);
                x=leftPosition;
                y=rightPosition;
                MotorFrontRight.setPower(0.8);
                MotorBackRight.setPower(0.8);
                leftPosition= MotorBackLeft.getCurrentPosition();
                rightPosition=MotorBackRight.getCurrentPosition();
                x=leftPosition;
                y=rightPosition;
                sleep(500);
                MotorBackLeft.setPower(0);
                MotorBackRight.setPower(0);
                MotorFrontRight.setPower(0);
                MotorFrontLeft.setPower(0);

                // Show the position of the motor on telemetry

            }



        }
    }
}
