package org.example.compilers.countries.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Area {
    @Setter
    private int square;
    private List<Area> areaList = new ArrayList<>();

    @Override
    public String toString() {
        return "Area{" +
                "square=" + square +
                ", areaList=" + areaList +
                '}';
    }
}
