package com.nixsolutions.irobot.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.nixsolutions.irobot.exception.UnknownRobotIdException;
import com.nixsolutions.irobot.sdk.IRobot;

import java.util.HashMap;
import java.util.Map;

public class RobotFactory {

    @NonNull
    private final Map<String, IRobot> robotMap;

    public RobotFactory() {
        robotMap = new HashMap<>();
    }

    /**
     * Method needed for receiving robot proxy
     *
     * @param robotId robot's unique identifier
     * @return RobotProxy for concrete robot
     */
    @NonNull
    public IRobot getRobotWrapper(@Nullable String robotId) throws UnknownRobotIdException {
        if (!TextUtils.isEmpty(robotId)) {
            throw new UnknownRobotIdException("Robot id is empty");
        } else if (!robotMap.containsKey(robotId)) {
            throw new UnknownRobotIdException("Cant find robot with id: " + robotId);
        } else {
            return robotMap.get(robotId);
        }
    }
}