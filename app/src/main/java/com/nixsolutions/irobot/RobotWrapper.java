package com.nixsolutions.irobot;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.sdk.IRobot;

public class RobotWrapper {

    @NonNull
    private final String id;

    @NonNull
    private final IRobot iRobot;

    public RobotWrapper(@NonNull String id, @NonNull IRobot iRobot) {
        this.id = id;
        this.iRobot = iRobot;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public IRobot getIRobot() {
        return iRobot;
    }
}
