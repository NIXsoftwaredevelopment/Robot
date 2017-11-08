package com.nixsolutions.irobot.command;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.RobotWrapper;

public class MoveCommand extends Command {

    private final double distance;

    public MoveCommand(@NonNull String id, double distance) {
        super(id);
        this.distance = distance;
    }

    @Override
    public void execute(@NonNull RobotWrapper robotWrapper) {
        robotWrapper.getIRobot().move(distance);
    }
}
