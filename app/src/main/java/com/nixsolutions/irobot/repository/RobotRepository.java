package com.nixsolutions.irobot.repository;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.RobotWrapper;
import com.nixsolutions.irobot.exception.UnknownRobotIdException;

import java.util.List;

public interface RobotRepository {

    @NonNull
    RobotWrapper findRobotById(@NonNull String id) throws UnknownRobotIdException;

    @NonNull
    List<RobotWrapper> findAll();
}
