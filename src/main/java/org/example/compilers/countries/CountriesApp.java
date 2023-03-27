package org.example.compilers.countries;

import org.example.compilers.countries.Controllers.AreaController;
import org.example.compilers.countries.views.AreaView;

public class CountriesApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        AreaController areaController = new AreaController(
                new AreaView(System.in)
        );

        areaController.parseData();
        areaController.outputData();
    }
}
