package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "LiftTest", group = "teleop")
public class LeftTest extends LinearOpMode {
    //motors
    private DcMotor MotorFrontLeft;
    private DcMotor MotorFrontRight;
    private DcMotor MotorBackLeft;
    private DcMotor MotorBackRight;
    private DcMotor liftLeft;

    //arm



    public void moveDriveTrain(){
        double vertical;
        double horizontal;
        double pivot;
        double lift;
        vertical = gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;
        lift=gamepad2.left_stick_y;
        MotorFrontRight.setPower(pivot -vertical + horizontal);
        MotorFrontLeft.setPower(pivot + (-vertical - horizontal));
        MotorBackRight.setPower(-pivot + (-vertical - horizontal));
        MotorBackLeft.setPower(-pivot + (-vertical + horizontal));


        //default speed
        MotorFrontLeft.setPower(.5);
        MotorFrontRight.setPower(.5);
        MotorBackLeft.setPower(.5);
        MotorBackRight.setPower(.5);


    }

    @Override
    public void runOpMode() throws InterruptedException {
//Initialize Drive Motors
        MotorFrontLeft = hardwareMap.dcMotor.get("MotorFrontLeft");
        MotorFrontRight = hardwareMap.dcMotor.get("MotorFrontRight");
        MotorBackLeft = hardwareMap.dcMotor.get("MotorBackLeft");
        MotorBackRight = hardwareMap.dcMotor.get("MotorBackRight");
        liftLeft = hardwareMap.dcMotor.get("liftLeft");

        MotorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);


        double speed = 0.6 * (gamepad1.left_trigger + 1) * (1 - gamepad1.right_trigger / 1.2);


        //rotateServo.setPosition(CLAW_NEUTURAL);

//2
        waitForStart();

        while (opModeIsActive()) {
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double drive = gamepad1.left_stick_y;
            double lift = gamepad2.left_stick_y;
            double speedMultiplier = .4 *((1-gamepad1.left_trigger*.8)+(1+gamepad1.right_trigger*.6));

            //forward and backward
            MotorFrontLeft.setPower(speedMultiplier * (drive - turn - strafe));
            MotorFrontRight.setPower(speedMultiplier * (drive + turn + strafe));
            MotorBackLeft.setPower(speedMultiplier * (drive - turn + strafe));
            MotorBackRight.setPower(speedMultiplier * (drive + turn - strafe));
           liftLeft.setPower(lift);
//claw rotation

            /*if (gamepad2.right_bumper)
            {
                rotateServo.setPosition(CLAW_NEUTURAL);
            }*/


            //claw open and close




            // {

            // }
            //stupid slide stuff
            // Left side motor for lift

            // RIGHT SIDE MOTOR FOR LIFT



        }
    }
}
