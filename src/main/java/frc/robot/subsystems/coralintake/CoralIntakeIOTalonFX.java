package frc.robot.subsystems.coralintake;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import frc.robot.Constants.CoralIntakeConstants;

public class CoralIntakeIOTalonFX implements CoralIntakeIO {

    private final TalonFX hinge_;
    private final TalonFX roller_;

    private final MotionMagicVoltage hingeRequest_;
    private final VelocityVoltage rollerRequest_;

    public CoralIntakeIOTalonFX() {
        // Initialization
        hinge_ = new TalonFX(CoralIntakeConstants.hingeMotorCAN);
        roller_ = new TalonFX(CoralIntakeConstants.rollerMotorCAN);
        hingeRequest_ = new MotionMagicVoltage(0);
        rollerRequest_ = new VelocityVoltage(0);

        // Hinge Configuration
        TalonFXConfiguration hingeConfiguration = new TalonFXConfiguration();
        hingeConfiguration.Slot0.kP = 0.5;
        hingeConfiguration.MotionMagic.MotionMagicCruiseVelocity = 60; // Target cruise velocity of 80 rps
        hingeConfiguration.MotionMagic.MotionMagicAcceleration = 80; // Target acceleration of 160 rps/s (0.5 seconds)
        hingeConfiguration.MotionMagic.MotionMagicJerk = 1600; // Target jerk of 1600 rps/s/s (0.1 seconds)

        // Roller Configuration
        TalonFXConfiguration rollerConfiguration = new TalonFXConfiguration();
        rollerConfiguration.Slot0.kP = 0.5;

        // Apply Configurations
        hinge_.getConfigurator().apply(hingeConfiguration);
        roller_.getConfigurator().apply(new Slot0Configs().withKP(0.5));
    }

    @Override
    public void runHingePosition(Angle angle) {
        hinge_.setControl(hingeRequest_.withPosition(angle));
    }

    @Override
    public void runRollerVelocity(AngularVelocity vel) {
        roller_.setControl(rollerRequest_.withVelocity(vel));
    }

    @Override
    public void stopRoller() {
        roller_.stopMotor();
    }

    @Override
    public void updateInputs(CoralIntakeInputsAutoLogged inputs) {
        inputs.hingeAcceleration = hinge_.getAcceleration().getValueAsDouble();
        inputs.hingeCurrent = hinge_.getStatorCurrent().getValueAsDouble();
        inputs.hingePosition = hinge_.getPosition().getValueAsDouble();
        inputs.hingeVelocity = hinge_.getPosition().getValueAsDouble();
        inputs.hingeVoltage = hinge_.getMotorVoltage().getValueAsDouble();

        inputs.rollerAcceleration = roller_.getAcceleration().getValueAsDouble();
        inputs.rollerCurrent = roller_.getStatorCurrent().getValueAsDouble();
        inputs.rollerPosition = roller_.getPosition().getValueAsDouble();
        inputs.rollerVelocity = roller_.getPosition().getValueAsDouble();
        inputs.rollerVoltage = roller_.getMotorVoltage().getValueAsDouble();
    }
    
}
