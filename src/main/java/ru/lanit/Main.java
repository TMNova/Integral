package ru.lanit;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<CalculationThread> threads = new ArrayList<CalculationThread>();

    public static void main(String[] args) throws InterruptedException {
        CalculationIntegral.calcWithThreads(0, 4, 2);
        System.out.println(CalculationIntegral.getSum());
    }
}