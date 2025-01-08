package frc.robot.subsystems.alnipulator;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlnipulatorConstants;

public class Alnipulator extends SubsystemBase {

    private final AlnipulatorIO io_;
    private final AlnipulatorInputsAutoLogged inputs_;

    public Alnipulator(AlnipulatorIO io) {
        io_ = io;
        inputs_ = new AlnipulatorInputsAutoLogged();
    }

    @Override
    public void periodic() {
        io_.updateInputs(inputs_);
        Logger.processInputs(getName(), inputs_);
    }

    public Command intakeDouble() {
        return runOnce(() -> {
            io_.spin(AlnipulatorConstants.spinVelocity);
        });
    }

    public Command intakeSingle() {
        return runOnce(() -> {
            io_.spinOne(AlnipulatorConstants.spinVelocity);
        });
    }

    public Command outtake() {
        return runOnce(() -> {
            io_.spin(AlnipulatorConstants.spinVelocity.unaryMinus());
        });
    }

    public Command outtakeSingle() {
        return runOnce(() -> {
            io_.spinOne(AlnipulatorConstants.spinVelocity.unaryMinus());
        });
    }

    public Command halt() { return runOnce(io_::stop); }

}
