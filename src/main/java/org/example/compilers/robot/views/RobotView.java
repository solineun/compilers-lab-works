package org.example.compilers.robot.views;

import lombok.RequiredArgsConstructor;
import org.example.compilers.robot.models.RobotModel;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class RobotView {

    private final InputStream source;

    public Scanner scanCommands() {
        return new Scanner(this.source);
    }

    public void outputResultAsStatesList(List<RobotModel> statesList) {
        for (var state : statesList) {
            System.out.println(state);
        }
    }

    public void outputResultAsMap(Map<RobotModel, String> statesMap) {
        System.out.println(statesMap);
    }

    public void outputResultAsTable(List<RobotModel> statesList, int height, int width) {


        for (int i = width; i > 0; i--) {
            for (int j = height; j > 0; j--) {
                RobotModel testState = null;
                for (var state : statesList) {
                    if (state.getX() == j && state.getY() == i) {
                        System.out.print("+  ");
                        testState = state;
                        break;
                    }
                }
                if (testState == null)
                    System.out.print("-  ");
            }
            System.out.println();
        }
    }
}
