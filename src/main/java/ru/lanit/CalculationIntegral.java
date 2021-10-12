package ru.lanit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CalculationIntegral {
    private static List<CalculationThread> threads = new ArrayList<CalculationThread>();
    private static List listCalc = Collections.synchronizedList(new ArrayList<>());
    private static double sum = 0;
    private static Lock lock = new ReentrantLock();


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
                threads.add(new CalculationThread(a, b));
                synchronized (threads.get(i)) {
                    threads.get(i).wait();
                }
                a -= interval;
                b -= interval;
            }
        }
        Thread.sleep(100);

        for (CalculationThread thread : threads) {
            if(thread.isAlive()) continue;
        }

    }

    public synchronized static void synchronizedIncrementSum(double value) {
        sum += value;
    }

    public static void lockThreadIncrementSum(double value) {
        lock.lock();
        try {
            sum += value;
        } finally {
            lock.unlock();
        }
    }

    public static double getSum() {
        return sum;
    }

}
