package com.nixsolutions.irobot.facade;

import com.nixsolutions.irobot.RobotWrapper;
import com.nixsolutions.irobot.command.BeepCommand;
import com.nixsolutions.irobot.command.MoveCommand;
import com.nixsolutions.irobot.command.TurnCommand;
import com.nixsolutions.irobot.repository.CommandRepository;
import com.nixsolutions.irobot.repository.MockCommandRepository;
import com.nixsolutions.irobot.repository.RobotRepository;
import com.nixsolutions.irobot.sdk.IRobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RobotFacadeTest {

    private static final String MOCK_DEVICE_ID_1 = UUID.randomUUID().toString();

    private static final String MOCK_DEVICE_ID_2 = UUID.randomUUID().toString();

    private static final String MOCK_DEVICE_ID_3 = UUID.randomUUID().toString();

    @Mock
    IRobot mockRobot1;

    @Mock
    IRobot mockRobot2;

    @Mock
    IRobot mockRobot3;

    @Mock
    private RobotRepository mockRobotRepository;

    private CommandRepository mockCommandRepository = spy(MockCommandRepository.class);

    private RobotFacade robotFacade;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        List<RobotWrapper> robotWrappers = new ArrayList<>();

        robotWrappers.add(new RobotWrapper(MOCK_DEVICE_ID_1, mockRobot1));
        robotWrappers.add(new RobotWrapper(MOCK_DEVICE_ID_2, mockRobot2));
        robotWrappers.add(new RobotWrapper(MOCK_DEVICE_ID_3, mockRobot3));

        when(mockRobotRepository.findRobotById(MOCK_DEVICE_ID_1))
                .thenReturn(robotWrappers.get(0));

        when(mockRobotRepository.findRobotById(MOCK_DEVICE_ID_2))
                .thenReturn(robotWrappers.get(1));

        when(mockRobotRepository.findRobotById(MOCK_DEVICE_ID_3))
                .thenReturn(robotWrappers.get(2));

        when(mockRobotRepository.findAll()).thenReturn(robotWrappers);

        robotFacade = new RobotFacadeImpl(mockCommandRepository, mockRobotRepository);
    }

    @Test
    public void testMove() throws Exception {
        robotFacade.move(MOCK_DEVICE_ID_1, 100D);
        verify(mockCommandRepository).save(any(MoveCommand.class));
        verify(mockRobot1).move(100D);
    }

    @Test
    public void testTurn() throws Exception {
        robotFacade.turn(MOCK_DEVICE_ID_2, 50D);
        verify(mockCommandRepository).save(any(TurnCommand.class));
        verify(mockRobot2).turn(50D);
    }

    @Test
    public void testBeep() throws Exception {
        robotFacade.beep(MOCK_DEVICE_ID_2);
        verify(mockCommandRepository).save(any(BeepCommand.class));
        verify(mockRobot2).beep();
    }

    @Test
    public void testRepeatForAll() throws Exception {
        robotFacade.move(MOCK_DEVICE_ID_2, 100D);
        robotFacade.turn(MOCK_DEVICE_ID_2, 50D);
        robotFacade.beep(MOCK_DEVICE_ID_2);

        robotFacade.repeatForAll();
        verify(mockRobot1).move(100D);
        verify(mockRobot2, times(2)).move(100D);
        verify(mockRobot3).move(100D);

        verify(mockRobot1).turn(50D);
        verify(mockRobot2, times(2)).turn(50D);
        verify(mockRobot3).turn(50D);

        verify(mockRobot1).beep();
        verify(mockRobot2, times(2)).beep();
        verify(mockRobot3).beep();
    }

    @Test
    public void testRepeatForIds() throws Exception {
        robotFacade.move(MOCK_DEVICE_ID_2, 100D);
        robotFacade.turn(MOCK_DEVICE_ID_2, 50D);
        robotFacade.beep(MOCK_DEVICE_ID_2);

        robotFacade.repeatForIds(MOCK_DEVICE_ID_1, MOCK_DEVICE_ID_3);

        verify(mockRobot1).move(100D);
        verify(mockRobot1).turn(50D);
        verify(mockRobot1).beep();

        verify(mockRobot3).move(100D);
        verify(mockRobot3).turn(50D);
        verify(mockRobot3).beep();
    }
}