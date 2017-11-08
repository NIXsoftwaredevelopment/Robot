package com.nixsolutions.irobot.command;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.RobotWrapper;

public abstract class Command {

    @NonNull
    private final String id;

    protected Command(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public abstract void execute(@NonNull RobotWrapper robotWrapper);
}
