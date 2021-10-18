package ru.lanit;

public class CalculationRunnable implements Runnable {
	private double a;
	private double b;
	private double interval;

	@Override
	public void run() {
		double value = 0;
		synchronized (this) {
			value = CalculationIntegral.calcSquareIntegral(a, b);
			a -= interval;
			b -= interval;
		}
		CalculationIntegral.incrementSum(value);
	}

	public void setA(double a) {
		this.a = a;
	}

	public void setB(double b) {
		this.b = b;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

}
