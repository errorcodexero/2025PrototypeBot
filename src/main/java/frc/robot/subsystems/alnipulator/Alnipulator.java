package frc.robot.subsystems.alnipulator;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Alnipulator extends SubsystemBase {

    private final AlnipulatorIO io_;
    private final AlnipulatorInputsAutoLogged inputs_;

    private final LoggedNetworkNumber spinSpeedRPS_;

    public Alnipulator(AlnipulatorIO io) {
        io_ = io;
        inputs_ = new AlnipulatorInputsAutoLogged();

        spinSpeedRPS_ = new LoggedNetworkNumber("Alnipulator/SpinSpeedRPS", 10);
    }

    @Override
    public void periodic() {
        io_.updateInputs(inputs_);
        Logger.processInputs(getName(), inputs_);
    }

    public Command intakeDouble() {
        return runOnce(() -> {
            io_.spin(latestVelocity());
        });
    }

    public Command intakeSingle() {
        return runOnce(() -> {
            io_.spinOne(latestVelocity());
        });
    }

    public Command outtake() {
        return runOnce(() -> {
            io_.spin(latestVelocity().unaryMinus());
        });
    }

    public Command outtakeSingle() {
        return runOnce(() -> {
            io_.spinOne(latestVelocity().unaryMinus());
        });
    }

    public Command halt() { return runOnce(io_::stop); }

    private AngularVelocity latestVelocity() {
        return RotationsPerSecond.of(spinSpeedRPS_.get());
    }

}
