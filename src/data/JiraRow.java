package data;

public class JiraRow {
	final private String key;
	final private Status status;
	final private String team;
	final private double estimateInDays;
	
	public JiraRow(final String key, final Status status, final String team, final double estimateInDays) {
		this.key = key;
		this.status = status;
		this.team = team;
		this.estimateInDays = estimateInDays;
	}
	
	public String getKey() {
		return key;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public String getTeam() {
		return team;
	}
	
	public double getEstimateInDays() {
		return estimateInDays;
	}
}
