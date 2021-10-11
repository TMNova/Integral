package ru.lanit;

import java.util.ArrayList;
import java.util.List;

class CalculationIntegral {
    private static List<CalculationThread> threads = new ArrayList<CalculationThread>();


    private static double f(double x) {
        return Math.sin(x);
    }

    public static double calcSquareIntegral(double a, double b) {
        double area = 0;
        double step = 0.01;
        for (int i = 0; i < (b - a) / step; i++) {
            area += step * f(a + i * step);
        }
        return area;
    }

    public static double calcWithThreads(double startPoint, double endPoint, int n) throws InterruptedException {
        double interval = (endPoint - startPoint) / n;
        double a = endPoint - interval;
        double b = endPoint;
        double sum = 0;

        for (int i = 0; i < n; i++) {
            if (a >= startPoint) {
                threads.add(new CalculationThread(a, b));
                a -= interval;
                b -= interval;
            }
        }

        for (CalculationThread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < CalculationList.listSize(); i++) {
            double value = (double) CalculationList.getValueByIndex(i);
            sum += value;
        }

        return sum;
    }
}
