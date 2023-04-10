package org.example.compilers.robot.controllers;

import lombok.RequiredArgsConstructor;
import org.example.compilers.robot.models.RobotModel;
import org.example.compilers.robot.models.State;
import org.example.compilers.robot.models.service.RobotService;
import org.example.compilers.robot.views.RobotView;

import java.util.*;

@RequiredArgsConstructor
public class RobotController {

    private final RobotView robotView;
    private final RobotService robotService;

    private Map<RobotModel, String> statesMap = new LinkedHashMap<>();

    private void addRobotState(RobotModel robotState, String command) {
<<<<<<< HEAD
        if (robotState.getState() != State.ERROR && !"\\w".equals(command)) {

        }
=======
>>>>>>> main
        RobotModel emptyRobot = RobotModel.getEmptyRobot();
        emptyRobot.setX(robotState.getX());
        emptyRobot.setY(robotState.getY());
        emptyRobot.setState(robotState.getState());

        statesMap.put(emptyRobot, command);
    }

    public void parseCommands()  {
        String[] commands = robotView.scanCommands().next().split("");
        stateMachine(commands);
    }

    private void stateMachine(String[] commands) {
<<<<<<< HEAD
        for (int i = 0; i < commands.length; i++) {
=======

        for (int i = 0; i < commands.length; i++) {

>>>>>>> main
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
