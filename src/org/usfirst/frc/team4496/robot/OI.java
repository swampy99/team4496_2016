package org.usfirst.frc.team4496.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick controller = new Joystick(0);
	public static Button buttonA = new JoystickButton(controller, 1),
			buttonB = new JoystickButton(controller, 2),
			buttonX = new JoystickButton(controller, 3),
			buttonY= new JoystickButton(controller, 4),
			buttonLeftBumper = new JoystickButton(controller, 5),
			buttonRightBumper = new JoystickButton(controller, 6),
			buttonSelect = new JoystickButton(controller, 7),
			buttonStart = new JoystickButton(controller, 8),
			buttonLeftJoystick = new JoystickButton(controller, 9),
			buttonRightJoystick = new JoystickButton(controller, 10);
}

