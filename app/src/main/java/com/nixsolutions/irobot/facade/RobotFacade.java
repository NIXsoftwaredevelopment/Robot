package com.nixsolutions.irobot.facade;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.exception.UnknownRobotIdException;

public interface RobotFacade {

    void move(@NonNull String id, double distance) throws UnknownRobotIdException;

    void turn(@NonNull String id, double distance) throws UnknownRobotIdException;

    void beep(@NonNull String id) throws UnknownRobotIdException;

    void repeatForAll();

    void repeatForIds(@NonNull String... ids) throws UnknownRobotIdException;
}
