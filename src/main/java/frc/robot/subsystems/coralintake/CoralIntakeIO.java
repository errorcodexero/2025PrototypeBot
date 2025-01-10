package frc.robot.subsystems.coralintake;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public interface CoralIntakeIO {

    @AutoLog
    public static class CoralIntakeInputs {
        public double hingePosition;
        public double hingeVelocity;
        public double hingeAcceleration;
        public double hingeCurrent;
        public double hingeVoltage;

        public double rollerPosition;
        public double rollerVelocity;
        public double rollerAcceleration;
        public double rollerCurrent;
        public double rollerVoltage;
    }

    public default void updateInputs(CoralIntakeInputsAutoLogged inputs) {};

    public default void runHingePosition(Angle angle) {};

    public default void runRollerVelocity(AngularVelocity vel) {};
    public default void stopRoller() {};

}
