package org.example.compilers.robot.models.service;
import org.example.compilers.robot.models.State;
import org.example.compilers.robot.models.RobotModel;

public class RobotService {
    private RobotModel robotModel;

    public RobotService(RobotModel robotModel) {
        this.robotModel = robotModel;
    }

    public RobotModel moveForward() {
        switch (robotModel.getState()) {
            case NORTH -> robotModel.setY(robotModel.getY() + 1);
            case SOUTH -> robotModel.setY(robotModel.getY() - 1);
            case EAST -> robotModel.setX(robotModel.getX() + 1);
            case WEST -> robotModel.setX(robotModel.getX() - 1);
        }
        return this.robotModel;
    }

    public RobotModel turnRight() {
        switch (robotModel.getState()) {
            case NORTH -> robotModel.setState(State.EAST);
            case EAST -> robotModel.setState(State.SOUTH);
            case SOUTH -> robotModel.setState(State.WEST);
            case WEST -> robotModel.setState(State.NORTH);
        }
        return this.robotModel;
    }

    public RobotModel turnLeft() {
        switch (robotModel.getState()) {
            case NORTH -> robotModel.setState(State.WEST);
            case WEST -> robotModel.setState(State.SOUTH);
            case SOUTH-> robotModel.setState(State.EAST);
            case EAST -> robotModel.setState(State.NORTH);
        }
        return this.robotModel;
    }

    public void printCoordinates() {
        var start = RobotModel.getStartingRobot();
        System.out.println("Start point: x = " + start.getX() + ", y = " + start.getY());
        System.out.println("Finish point: x = " + robotModel.getX() + ", y = " + robotModel.getY());
    }

    public RobotModel setError() {
        robotModel.setState(State.ERROR);
        return this.robotModel;
    }
    public RobotModel setTerminate() {
        robotModel.setState(State.TERMINATE);
        return this.robotModel;
    }
    public RobotModel setFinish() {
        robotModel.setState(State.FINISH);
        return this.robotModel;
    }
}
