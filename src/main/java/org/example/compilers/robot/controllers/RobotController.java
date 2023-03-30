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

    private Map<RobotModel, String> statesMap = new LinkedHashMap<>();

    private void addRobotState(RobotModel robotState, String command) {
        RobotModel emptyRobot = RobotModel.getEmptyRobot();

        emptyRobot.setX(robotState.getX());
        emptyRobot.setY(robotState.getY());
        emptyRobot.setState(robotState.getState());

        statesMap.put(emptyRobot, command);
    }

    public void parseCommands()  {
        String[] commands = robotView.scanCommands().next().split("");
        String[] prevAndCur = new String[] {"", ""};

        for (int i = 0; i < commands.length; i++) {
            prevAndCur[0] = prevAndCur[1];
            prevAndCur[1] = commands[i];
            if (prevAndCur[0].equals(".")) {
                addRobotState(robotService.setError(), commands[i]);
                System.out.println("ERROR: There should be nothing after terminating character ");
                break;
            } else if (i == commands.length - 1 && prevAndCur[1] != ".") {
                addRobotState(robotService.setError(), commands[i]);
                System.out.println("ERROR: No terminating character");
                break;
            }

            switch (commands[i]) {
                case "f" -> {
                    addRobotState(robotService.moveForward(), commands[i]);
                }
                case "r" -> {
                    addRobotState(robotService.turnRight(), commands[i]);
                }
                case "l" -> {
                    addRobotState(robotService.turnLeft(), commands[i]);
                }
                case "." -> {
                    addRobotState(robotService.setTerminate(), commands[i]);
                }
                default -> {
                    addRobotState(robotService.setError(), commands[i]);
                    System.out.println("ERROR: Unexpected character");
                }
            }
        }
        addRobotState(robotService.setFinish(), "\\w");
    }

    public void printResult() {
        robotService.printCoordinates();
        robotView.outputResultAsMap(statesMap);
        statesMap = new LinkedHashMap<>();
    }
}
