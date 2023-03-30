package org.example.compilers.robot.controllers;

import lombok.RequiredArgsConstructor;
import org.example.compilers.robot.models.RobotModel;
import org.example.compilers.robot.models.service.RobotService;
import org.example.compilers.robot.views.RobotView;

import java.util.*;

@RequiredArgsConstructor
public class RobotController {

    private final RobotView robotView;
    private final RobotService robotService;

    private Map<RobotModel, Character> statesMap = new LinkedHashMap<>();

    private void addRobotState(RobotModel robotState, char command) {
        RobotModel emptyRobot = RobotModel.getEmptyRobot();

        emptyRobot.setX(robotState.getX());
        emptyRobot.setY(robotState.getY());
        emptyRobot.setState(robotState.getState());

        statesMap.put(emptyRobot, command);
    }

    public void parseCommands()  {
        try {
            char[] commands = robotView.scanCommands().next().toCharArray();
            char[] prevAndCur = new char[] {' ', ' '};

            for (int i = 0; i < commands.length; i++) {
                prevAndCur[0] = prevAndCur[1];
                prevAndCur[1] = commands[i];
                if (prevAndCur[0] == '.') {
                    addRobotState(robotService.setError(), commands[i]);
                    throw new NotEmptyAfterTermCharException("ERROR: There should be nothing after terminating character ");
                } else if (i == commands.length - 1 && prevAndCur[1] != '.') {
                    addRobotState(robotService.setError(), commands[i]);
                    throw new NoTerminatingCharacterException("ERROR: No terminating character");
                }

                switch (commands[i]) {
                    case 'f' -> {
                        addRobotState(robotService.moveForward(), commands[i]);
                    }
                    case 'r' -> {
                        addRobotState(robotService.turnRight(), commands[i]);
                    }
                    case 'l' -> {
                        addRobotState(robotService.turnLeft(), commands[i]);
                    }
                    case '.' -> {
                        addRobotState(robotService.setTerminate(), commands[i]);
                    }
                    default -> {
                        addRobotState(robotService.setError(), commands[i]);
                        throw new UnexpectedCharacterException("ERROR: Unexpected character");
                    }
                }
            }
            addRobotState(robotService.setFinish(), '\\');
        } catch (UnexpectedCharacterException e) {
            System.out.println(e.getMessage());
        } catch (NoTerminatingCharacterException e) {
            System.out.println(e.getMessage());
        } catch (NotEmptyAfterTermCharException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendResult() {
        robotView.outputResultAsMap(statesMap);
        statesMap = new LinkedHashMap<>();
    }
}
