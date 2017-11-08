package com.nixsolutions.irobot.command;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.RobotWrapper;

public class BeepCommand extends Command {

    public BeepCommand(@NonNull String id) {
        super(id);
    }

    @Override
    public void execute(@NonNull RobotWrapper robotWrapper) {
        robotWrapper.getIRobot().beep();
    }
}
