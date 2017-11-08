package com.nixsolutions.irobot.repository;


import android.support.annotation.NonNull;

import com.nixsolutions.irobot.command.Command;

import java.util.ArrayList;
import java.util.List;

public class MockCommandRepository implements CommandRepository {

    private final List<Command> commandList = new ArrayList<>();

    @Override
    public void save(@NonNull Command command) {
        commandList.add(command);
    }

    @NonNull
    @Override
    public List<Command> findAll() {
        return commandList;
    }
}
