package data;

public class Effort {
	final private double total;
	final private double remaining;
	
	public Effort(final double total, final double remaining) {
		this.total = total;
		this.remaining = remaining;
	}
	
	public double getTotalEffort() {
		return total;
	}
	
	public double getRemainingEffort() {
		return remaining;
	}
}
