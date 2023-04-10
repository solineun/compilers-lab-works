package org.example.compilers.countries.сontrollers;

import lombok.RequiredArgsConstructor;
import org.example.compilers.countries.models.Area;
import org.example.compilers.countries.views.AreaView;

import java.util.*;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class AreaController {
    private final AreaView areaView;
    private List<Area> areaList;
    public void parseData() {
        String[] data = areaView.scanData().next().split("");
        stateMachine(data);
    }

    private void stateMachine(String[] data) {
        Queue<String> stack = new ArrayDeque<>();
    }

    public void outputData() {
        areaView.outputResult(areaList);
    }
}
