package com.nixsolutions.irobot.repository;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.command.Command;

import java.util.List;

public interface CommandRepository {

    void save(@NonNull Command command);

    @NonNull
    List<Command> findAll();
}
