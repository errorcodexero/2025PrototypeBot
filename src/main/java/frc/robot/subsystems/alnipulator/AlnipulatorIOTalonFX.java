package frc.robot.subsystems.alnipulator;

import com.ctre.phoenix6.controls.CoastOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.AngularVelocity;

public class AlnipulatorIOTalonFX implements AlnipulatorIO {

    private final TalonFX leftMotor = new TalonFX(0);
    private final TalonFX rightMotor = new TalonFX(1);

    @Override
    public void spin(AngularVelocity vel) {
        leftMotor.setControl(new VelocityVoltage(vel));
        rightMotor.setControl(new VelocityVoltage(vel));
    }

    @Override
    public void spinOne(AngularVelocity vel) {
        leftMotor.setControl(new CoastOut());
        rightMotor.setControl(new VelocityVoltage(vel));
    }

    @Override
    public void stop() {
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    public void cruise() {
        leftMotor.setControl(new CoastOut());
        rightMotor.setControl(new CoastOut());
    }

    @Override
    public void updateInputs(AlnipulatorInputsAutoLogged inputs) {
        // TODO Auto-generated method stub
        
    }
    
}
