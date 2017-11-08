package com.nixsolutions.irobot.command;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.RobotWrapper;

public class TurnCommand extends Command {

    private final double angle;

    public TurnCommand(@NonNull String id, double angle) {
        super(id);
        this.angle = angle;
    }

    @Override
    public void execute(@NonNull RobotWrapper robotWrapper) {
        robotWrapper.getIRobot().turn(angle);
    }
}
