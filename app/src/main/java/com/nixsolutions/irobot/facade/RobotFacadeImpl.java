package com.nixsolutions.irobot.facade;

import android.support.annotation.NonNull;

import com.nixsolutions.irobot.RobotWrapper;
import com.nixsolutions.irobot.command.BeepCommand;
import com.nixsolutions.irobot.command.Command;
import com.nixsolutions.irobot.command.MoveCommand;
import com.nixsolutions.irobot.command.TurnCommand;
import com.nixsolutions.irobot.exception.UnknownRobotIdException;
import com.nixsolutions.irobot.repository.CommandRepository;
import com.nixsolutions.irobot.repository.RobotRepository;

import java.util.ArrayList;
import java.util.List;

public class RobotFacadeImpl implements RobotFacade {

    @NonNull
    private final CommandRepository commandRepository;

    @NonNull
    private final RobotRepository robotRepository;

    public RobotFacadeImpl(@NonNull CommandRepository commandRepository,
                           @NonNull RobotRepository robotRepository) {
        this.commandRepository = commandRepository;
        this.robotRepository = robotRepository;
    }

    @Override
    public void move(@NonNull String id, double distance) throws UnknownRobotIdException {
        Command command = new MoveCommand(id, distance);
        executeCommand(command);
    }

    @Override
    public void turn(@NonNull String id, double distance) throws UnknownRobotIdException {
        Command command = new TurnCommand(id, distance);
        executeCommand(command);
    }

    @Override
    public void beep(@NonNull String id) throws UnknownRobotIdException {
        Command command = new BeepCommand(id);
        executeCommand(command);
    }

    @Override
    public void repeatForAll() {
        repeatForRobots(robotRepository.findAll());
    }

    @Override
    public void repeatForIds(@NonNull String... ids) throws UnknownRobotIdException {
        List<RobotWrapper> robotWrappers = new ArrayList<>();
        for (String id : ids) {
            RobotWrapper robotById = robotRepository.findRobotById(id);
            robotWrappers.add(robotById);
        }

        repeatForRobots(robotWrappers);
    }

    private void repeatForRobots(@NonNull List<RobotWrapper> robotWrappers) {
        List<Command> allCommandsList = commandRepository.findAll();
        for (Command command : allCommandsList) {
            for (RobotWrapper robotWrapper : robotWrappers) {
                command.execute(robotWrapper);
            }
        }
    }

    private void executeCommand(@NonNull Command command) throws UnknownRobotIdException {
        RobotWrapper robotWrapper = robotRepository.findRobotById(command.getId());
        command.execute(robotWrapper);

        commandRepository.save(command);
    }
}
