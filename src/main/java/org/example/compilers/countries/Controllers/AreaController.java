package org.example.compilers.countries.Controllers;

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
    public void parseData() {
        try (Scanner scanner = areaView.scanData()) {
            char[] data = scanner.next().toCharArray();
            areaList = getAreas(data);
        } catch (CurlyBraceException e) {
            System.out.println(e.getMessage());
        } catch (UnexpextedCharacterException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectSquareNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    public void outputData() {
        areaView.outputResult(areaList);
    }

    private List<Area> getAreas(char[] data) throws CurlyBraceException, UnexpextedCharacterException, IncorrectSquareNumberException {
        Area area = new Area();
        List<Area> areaList = new ArrayList<>();

        if (data[0] != '{') {
            throw new CurlyBraceException("ERROR: { missing");
        } else if (data[data.length - 1] != '}') {
            throw new CurlyBraceException("ERROR: } missing");
        } else if (data[1] == '{'){
            throw new CurlyBraceException("ERROR: There must be a square number after '{'");
        } else {
            Pattern pattern = Pattern.compile("[0-9]");
            int squareNumber = 0;
            int digitCount = 0;
            for (int i = 1; i < data.length - 1; i++) {
                if (pattern.matcher(String.valueOf(data[i])).matches()) {
                    squareNumber += Integer.parseInt(String.valueOf(data[i])) * Math.pow(10, 2 - digitCount);
                    digitCount++;
                } else if (data[i] == '{') {
                    area.setSquare(squareNumber);
                    area.getAreaList().addAll(
                            getAreas(
                                    Arrays.copyOfRange(data, i, data.length - 1)
                            )
                    );
                    squareNumber = 0;
                    digitCount = 0;
                    areaList.add(area);
                    area = new Area();
                    int index = getIndexOfClosingBrace(data) + 1;
                    if (i < index) {
                        i = index;
                    }
                } else if (digitCount > 3) {
                    throw new IncorrectSquareNumberException("ERROR: Square number must be < 999");
                } else if (data[i] == ',') {
                    area.setSquare(squareNumber);
                    areaList.add(area);
                    area = new Area();
                    squareNumber = 0;
                    digitCount = 0;
                    if (!pattern.matcher(String.valueOf(data[i + 1])).matches()) {
                        throw new UnexpextedCharacterException("ERROR: There must be a number after ','");
                    }
                } else if (data[i] == '}') {
                    area.setSquare(squareNumber);
                    areaList.add(area);
                    return areaList;
                } else {
                    throw new UnexpextedCharacterException("ERROR: Unexpected character");
                }
            }
        }
        return new ArrayList<>();
    }

    private int getIndexOfClosingBrace(char[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '}') {
                return i;
            }
        }
        return -1;
    }
}
