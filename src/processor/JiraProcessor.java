package processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import data.Effort;
import data.JiraRow;
import data.Status;
import data.TeamSummary;

public class JiraProcessor {
	public List<TeamSummary> process(final List<JiraRow> data) {
		// TODO
		// Group by the rows in the incoming data stream and aggregate the estimateInDays to give the total and remaining effort.
		// If the status is InProgress, add the estimate to total and remaining effort.
		// If the status is Closed, add the estimate to only total and NOT remaining effort.
		final Map<String, TeamSummary> aggregatedMap = new HashMap<>();
		data.forEach(jiraRow -> aggregateResult(jiraRow, aggregatedMap));
		return aggregatedMap.entrySet().stream().map(e -> new TeamSummary(e.getKey(), e.getValue().getEffort()))
				.collect(Collectors.toList());
	}
	
	private void aggregateResult(final JiraRow jiraRow, final Map<String, TeamSummary> aggregatedMap) {

		TeamSummary team = aggregatedMap.containsKey(jiraRow.getTeam())? aggregatedMap.get(jiraRow.getTeam()) : null;
		aggregatedMap.put(jiraRow.getTeam(),
				new TeamSummary(jiraRow.getTeam(), Status.InProgress.equals(jiraRow.getStatus())
						? new Effort(jiraRow.getEstimateInDays() + getTotalEffort(team),
								jiraRow.getEstimateInDays() + getRemainingEffort(team))
						: new Effort(jiraRow.getEstimateInDays() + getTotalEffort(team), getRemainingEffort(team))));
	}

	private double getTotalEffort(final TeamSummary team) {
		return team != null ? team.getEffort().getTotalEffort() : 0;
	}

	private double getRemainingEffort(final TeamSummary team) {
		return team != null ? team.getEffort().getRemainingEffort() : 0;
	}

}
