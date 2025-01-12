package frc.robot.subsystems.coralintake;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntake extends SubsystemBase {
    
    private final CoralIntakeIO io_;
    private final CoralIntakeInputsAutoLogged inputs_;

    private final LoggedNetworkNumber rollerRPS_ = new LoggedNetworkNumber("rollerRPS", 10);
    private final LoggedNetworkNumber hingePosition_ = new LoggedNetworkNumber("hingeAngle", 90);

    public CoralIntake(CoralIntakeIO io) {
        io_ = io;
        inputs_ = new CoralIntakeInputsAutoLogged();
    }

    @Override
    public void periodic() {
        io_.updateInputs(inputs_);
        Logger.processInputs(getName(), inputs_);
    }

    public Command intake() {
        return startEnd(() -> {
            io_.runHingePosition(Degrees.of(hingePosition_.get()));
            io_.runRollerVelocity(RotationsPerSecond.of(rollerRPS_.get()));
        }, () -> {
            io_.runHingePosition(Degrees.zero());
            io_.stopRoller();
        });
    } 

}
