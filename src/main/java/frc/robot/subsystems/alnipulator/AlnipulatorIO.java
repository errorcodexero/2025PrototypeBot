package frc.robot.subsystems.alnipulator;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.AngularVelocity;

public interface AlnipulatorIO {
    
    @AutoLog
    public static class AlnipulatorInputs {
        public double leftPosition;
        public double leftVelocity;
        public double leftAcceleration;
        public double leftCurrent;
        public double leftVoltage;

        public double rightPosition;
        public double rightVelocity;
        public double rightAcceleration;
        public double rightCurrent;
        public double rightVoltage;
    }

    public void spin(AngularVelocity vel);
    public void spinOne(AngularVelocity vel);

    public void cruise();
    public void stop();

    public void updateInputs(AlnipulatorInputsAutoLogged inputs);

}
