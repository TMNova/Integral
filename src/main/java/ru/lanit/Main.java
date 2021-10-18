package ru.lanit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CalculationIntegral.calcWithThreads(0, 4, 4);
        System.out.println(CalculationIntegral.getSum());
    }
}