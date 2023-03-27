package org.example.compilers.robot.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"barrierHeight", "barrierWidth"})
public class RobotModel {
    private int x;
    private int y;
    private Direction direction;

    private final int barrierHeight = 9;
    private final int barrierWidth = 9;

    private RobotModel(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    private RobotModel() {}

    public static RobotModel getStartingRobot() {
        return new RobotModel(5, 5, Direction.NORTH);
    }

    public static RobotModel getEmptyRobot() {
        return new RobotModel();
    }
}

