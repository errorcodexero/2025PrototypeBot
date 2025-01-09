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

    public default void spin(AngularVelocity vel) {};
    public default void spinOne(AngularVelocity vel) {};

    public default void cruise() {};
    public default void stop() {};

    public default void updateInputs(AlnipulatorInputsAutoLogged inputs) {};

}
