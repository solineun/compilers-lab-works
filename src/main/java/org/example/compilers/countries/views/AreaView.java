package org.example.compilers.countries.views;

import lombok.RequiredArgsConstructor;
import org.example.compilers.countries.models.Area;

import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class AreaView {
    private final InputStream source;

    public Scanner scanData() {
        return new Scanner(source);
    }
    public void outputResult(List<Area> areaList) {
        System.out.println(areaList.get(0));
    }
}
