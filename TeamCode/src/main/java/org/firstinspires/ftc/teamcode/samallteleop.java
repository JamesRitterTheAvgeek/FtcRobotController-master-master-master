package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "smallteleop", group = "teleop")
public class samallteleop extends LinearOpMode {
    //motors
    private DcMotor MotorFrontLeft;
    private DcMotor MotorFrontRight;
    private DcMotor MotorBackLeft;
    private DcMotor MotorBackRight;

    private DcMotor liftArm;
    //slides
    private DcMotor liftLeft;
    private DcMotor liftRight;
    //private DcMotor spoolLift;
    //claw
    private Servo pushBot;
    private Servo armServo;
    private Servo rotateServo;
    //plen
    private Servo planeLaunch;

    private static final double PLEN_LAUNCH = 1;
    private static final double CLAW_UP = 0.6;

    private static final double CLAW_DOWN = 0.0;

    private static final double CLAW_NEUTURAL = 0;

    private static final double ARM_RETRACTED_POSITION = 0.1;
    private static final double ARM_EXTENDED_POSITION = 0.8;


    public void moveDriveTrain(){
        double vertical;
        double horizontal;
        double pivot;
        vertical = gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;
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
        MotorFrontLeft = hardwareMap.dcMotor.get("leftFront");
        MotorFrontRight = hardwareMap.dcMotor.get("rightFront");
        MotorBackLeft = hardwareMap.dcMotor.get("leftRear");
        MotorBackRight = hardwareMap.dcMotor.get("rightRear");
        //liftLeft = hardwareMap.dcMotor.get("liftLeft");
        //liftRight = hardwareMap.dcMotor.get("liftRight");
        //spoolLift=hardwareMap.dcMotor.get("spoolLift");
        liftArm= hardwareMap.dcMotor.get("liftArm");
        //liftLeft = hardwareMap.dcMotor.get("liftLeft");
        //  liftRight = hardwareMap.dcMotor.get("liftRight");""
//declare arm servo
        armServo = hardwareMap.servo.get("clawServo");
        planeLaunch=hardwareMap.servo.get("coolPlen");
        //rotateServo = hardwareMap.servo.get("rotateServo");

        MotorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        MotorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);


        double speed = 0.6 * (gamepad1.left_trigger + 1) * (1 - gamepad1.right_trigger / 1.2);


        armServo.setPosition(ARM_RETRACTED_POSITION);
        //rotateServo.setPosition(CLAW_NEUTURAL);

//2
        waitForStart();

        while (opModeIsActive()) {
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double drive = gamepad1.left_stick_y;
            double speedMultiplier = .4 *((1-gamepad1.left_trigger*.8)+(1+gamepad1.right_trigger*.6));
            double servoPower=0;
            double lift=gamepad2.right_stick_y;
            //forward and backward
            MotorFrontLeft.setPower(speedMultiplier * (drive - turn - strafe));
            MotorFrontRight.setPower(speedMultiplier * (drive + turn + strafe));
            MotorBackLeft.setPower(speedMultiplier * (drive - turn + strafe));
            MotorBackRight.setPower(speedMultiplier * (drive + turn - strafe));
           //iftLeft.setPower(hang);
           //iftRight.setPower(-hang);
            liftArm.setPower(lift/1.5);

//claw rotation
            if (gamepad2.a)
            {
                rotateServo.setPosition(CLAW_UP);
            }

            if (gamepad2.b)
            {
                rotateServo.setPosition(CLAW_DOWN);
            }
            /*if (gamepad2.right_bumper)
            {
                rotateServo.setPosition(CLAW_NEUTURAL);
            }*/


            //claw open and close
            /*if (gamepad2.dpad_up){
                spoolLift.setPower(1);
            }else if (gamepad2.dpad_down){
                spoolLift.setPower(-1);
            }else {
                spoolLift.setPower(0);
            }*/

            if (gamepad2.x)
            {
                servoPower+=0.1;
                armServo.setPosition(1);
            }
            if (gamepad2.y)
            {
                servoPower-=.1;
                armServo.setPosition(0.1);
            }
            if (gamepad2.dpad_up){
                armServo.setPosition(CLAW_NEUTURAL);
            }
            //PLEN PLEN PLEN PLEN LAUNCH I LOVE PLANE -JAMES
            if(gamepad2.left_bumper){
                planeLaunch.setPosition(PLEN_LAUNCH);
            }
            if(gamepad2.right_bumper){
                planeLaunch.setPosition(0);
            }

            // {

            // }
            //stupid slide stuff
            // Left side motor for lift

            // RIGHT SIDE MOTOR FOR LIFT


        }
    }
}
