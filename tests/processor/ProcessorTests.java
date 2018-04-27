package processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import data.TeamSummary;
import reader.JiraReader;
import testdata.Data;

public class ProcessorTests {

	@Test
	public void testProcess() throws Exception {
		// TODO
		// Read the data in Data.raw string and process the List of JiraRow
		// objects to produce a List of TeamSummary objects.
		// Each object in this List of List objects should exist in Data.result
		// stream.
		try (final BufferedReader reader = new BufferedReader(new StringReader(Data.raw))) {
			final JiraProcessor jiraProcessor = new JiraProcessor();
			final List<TeamSummary> teamSummaryList = jiraProcessor.process(new JiraReader().read(reader));

			Data.result.forEach(teamSummary -> {
				try {
					TeamSummary result = getRow(teamSummaryList.stream(), teamSummary.getTeam());
					assertEquals(teamSummary.getEffort().getRemainingEffort(), result.getEffort().getRemainingEffort(),0.0000001);
					assertEquals(teamSummary.getEffort().getTotalEffort(), result.getEffort().getTotalEffort(),0.0000001);
				} catch (Exception e) {
					fail("Exception caught while iterating list");
				}
			});
		}

	}

	private TeamSummary getRow(final Stream<TeamSummary> rows, final String team) throws Exception {
		return rows.filter(j -> j.getTeam().equals(team)).findFirst()
				.orElseThrow(() -> new Exception(String.format("%s not present", team)));
	}

}
