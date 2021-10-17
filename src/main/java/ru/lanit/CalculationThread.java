package ru.lanit;

public class CalculationThread extends Thread {
	private double a;
	private double b;
	private int threadNumber;


	public CalculationThread(double a, double b, int threadNumber) {
		this.a = a;
		this.b = b;
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		double value = CalculationIntegral.calcSquareIntegral(a, b);
		CalculationIntegral.incrementSum(value);
	}

}
