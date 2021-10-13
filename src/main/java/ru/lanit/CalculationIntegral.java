package ru.lanit;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

class CalculationIntegral {
    private static List<CalculationThread> threads = new ArrayList<CalculationThread>();
    private static List listCalc = Collections.synchronizedList(new ArrayList<>());
    private static double sum = 0;
    private static double oldValue = 0;
    private static boolean firstIteration = true;

    private static double f(double x) {
        return Math.sin(x);
    }

    public static double calcSquareIntegral(double a, double b) {
        double area = 0;
        double step = 0.01;
        for (int i = 0; i < (b - a) / step; i++) {
            area += step * f(a + i * step);
        }
        listCalc.add(area);
        return area;
    }

    public static void calcWithThreads(double startPoint, double endPoint, int n) throws InterruptedException {
        double interval = (endPoint - startPoint) / n;
        double a = endPoint - interval;
        double b = endPoint;
        double sum = 0;

        for (int i = 0; i < n; i++) {
            if (a >= startPoint) {
                threads.add(new CalculationThread(a, b, i));
                threads.get(i).start();
                a -= interval;
                b -= interval;
            }
        }

        for (CalculationThread thread : threads) {
            while (thread.isAlive()) {
                synchronized (thread) {
                    thread.notify();
                }
                continue;
            }
        }

    }

    public static void incrementSum(double value, int threadNumber) throws InterruptedException {
        synchronized (threads.get(threadNumber)) {
            threads.get(threadNumber).wait();
            sum += value;
        }
    }

    public static double getSum() {
        return sum;
    }

    public static double getOldValue() {
        return oldValue;
    }

    public static boolean isFirstIteration() {
        return firstIteration;
    }

    public static void setFalseFirstIteration() {
        firstIteration = false;
    }

}
