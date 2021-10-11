package ru.lanit;

public class CalculationThread extends Thread {
	private double a;
	private double b;
	private int n;

	public CalculationThread(double a, double b) {
		this.a = a;
		this.b = b;
		this.n = n;
		run();
	}

	@Override
	public void run() {
		CalculationList.add(CalculationIntegral.calcSquareIntegral(a, b));
	}

}
