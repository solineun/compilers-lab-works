package org.example.compilers.robot.apps;

import org.example.compilers.robot.controllers.RobotController;
import org.example.compilers.robot.models.RobotModel;
import org.example.compilers.robot.models.service.RobotService;
import org.example.compilers.robot.views.RobotView;

public class RobotApp {

    private static RobotController robotController;
    public static void run() {
        while (true) {
            robotController = new RobotController(
                    new RobotView(System.in),
                    new RobotService(RobotModel.getStartingRobot())
            );
            robotController.parseCommands();
            robotController.printResult();
        }
    }
}
