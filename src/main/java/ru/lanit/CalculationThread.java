package ru.lanit;

public class CalculationThread extends Thread {
	private double a;
	private double b;
	private int n;


	public CalculationThread(double a, double b) {
		this.a = a;
		this.b = b;
		this.n = n;
		start();
	}

	@Override
	public void run() {
		CalculationIntegral.incrementSum(CalculationIntegral.calcSquareIntegral(a, b));
	}

}
