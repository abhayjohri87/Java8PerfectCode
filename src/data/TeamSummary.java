package data;

public class TeamSummary {
	final private String team;
	final private Effort effort;
	
	public TeamSummary(final String team, final Effort effort) {
		this.team = team;
		this.effort = effort;
	}
	
	public String getTeam() {
		return team;
	}
	
	public Effort getEffort() {
		return effort;
	}
}
