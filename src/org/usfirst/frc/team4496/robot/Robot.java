
package org.usfirst.frc.team4496.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;

		//###################Ben was here
		//start of added code
		Solenoid grabberArm, catapultArm;
		Compressor mainCompressor;
		RobotDrive mainDrive, testDrive;
		Victor liftDrive;
		//DigitalInput upperSwitch, lowerSwitch;

	    /**
	     * This function is run when the robot is first started up and should be
	     * used for any initialization code.
     	*/
    public void robotInit() {
    	
    	// instantiate the command used for the autonomous period
        
        //mainDrive = new RobotDrive(RobotMap.leftFrontMotor, RobotMap.leftRearMotor, RobotMap.rightFrontMotor, RobotMap.rightFrontMotor);
        mainDrive = new RobotDrive(0, 1, 2, 3);
        mainDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        mainDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        liftDrive = new Victor(4);
        //upperSwitch = new DigitalInput(0);
        //lowerSwitch = new DigitalInput(1);
        
        
        //pnumatics declarations
        mainCompressor = new Compressor();
        mainCompressor.setClosedLoopControl(false);
        grabberArm = new Solenoid(0);
        catapultArm = new Solenoid(1);
        
        
    }
	
		/**
		 * This function is called once each time the robot enters Disabled mode.
		 * You can use it to reset any subsystem information you want to clear when
		 * the robot is disabled.
		 */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    }

    /**
     * This function is called periodically during operator control
     */
    @SuppressWarnings("deprecation")
	public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //main drive setup
        
        //Getting and rounding the input values
        double lXVal = OI.controller.getRawAxis(0);
        double lYVal = OI.controller.getRawAxis(1);
        double rXVal = OI.controller.getRawAxis(4);
        lXVal = ((double)((int)(lXVal  * 10)) ) / 20;
        lYVal = ((double)((int)(lYVal  * 10)) ) / 20;
        rXVal = ((double)((int)(rXVal  * 10)) ) / 20;
        
        //SmartDashboard output
        SmartDashboard.putDouble("LeftStickXValue", lXVal);
        SmartDashboard.putDouble("LeftStickYValue", lYVal);
        SmartDashboard.putDouble("RightStickXValue", rXVal);
        SmartDashboard.putInt("POVPad", OI.controller.getPOV());
        SmartDashboard.putBoolean("Compressor Status", !mainCompressor.getPressureSwitchValue());
        
        //main drive controls
        mainDrive.mecanumDrive_Cartesian(lXVal, lYVal, rXVal, 0);
        
        //Compressor controls
        if(!mainCompressor.getPressureSwitchValue()){
        	mainCompressor.start();
        } else {
        	mainCompressor.stop();
        }
        
        //Grabber arm controls
        if(OI.controller.getRawButton(1)){
        	grabberArm.set(true);
        } 
        if(OI.controller.getRawButton(2)) {
        	grabberArm.set(false);
        }
        
        //Catapult controls
        if(OI.controller.getRawButton(6)){
        	catapultArm.set(false);
        } else {
        	catapultArm.set(true);
        }
        
        //Lifter Code
        if(OI.controller.getPOV() == 180){
        	liftDrive.set(-.5);
        } else if(OI.controller.getPOV() == 0) {
        	liftDrive.set(.5);
        } else {
        	liftDrive.set(0);
        }
       
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}
