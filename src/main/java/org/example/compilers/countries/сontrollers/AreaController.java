package org.example.compilers.countries.сontrollers;

import lombok.RequiredArgsConstructor;
import org.example.compilers.countries.models.Area;
import org.example.compilers.countries.views.AreaView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class AreaController {
    private final AreaView areaView;
    private List<Area> areaList;
    public void parseData() {}

    public void outputData() {
        areaView.outputResult(areaList);
    }
}
