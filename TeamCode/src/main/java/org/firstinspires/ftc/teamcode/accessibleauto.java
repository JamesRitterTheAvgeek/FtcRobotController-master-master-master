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
        MotorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Turn the motor back on when we are done
        double leftPosition= MotorBackLeft.getCurrentPosition();
        double rightPosition=MotorBackRight.getCurrentPosition();

        telemetry.update();
        waitForStart();

        while (true) {
            // Get the current position of the motor
            while (true) {
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                telemetry.update();
                MotorBackLeft.setPower(-0.8);
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                telemetry.update();
                MotorBackRight.setPower(0.8);
                telemetry.addData("EncoderL Position", leftPosition);
                telemetry.addData("EncoderR Position", rightPosition);
                telemetry.update();
                MotorFrontLeft.setPower(0.8);
                MotorFrontRight.setPower(-0.8);
                sleep(3000);
                MotorFrontRight.setPower(-0.8);
                MotorBackRight.setPower(-0.8);
                sleep(1000);

                // Show the position of the motor on telemetry

            }
        }


    }
}
