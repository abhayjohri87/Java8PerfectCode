package reader;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import data.JiraRow;
import data.Status;
import testdata.Data;

public class JiraReaderTests {
	
	@Test
	public void testRead() throws Exception {
		try(final BufferedReader reader = new BufferedReader(new StringReader(Data.raw))) {
			final JiraReader jiraReader = new JiraReader();
			final List<JiraRow> rows = jiraReader.read(reader);
			
			assertEquals(18, rows.size());
			
			final JiraRow row10121 = getRow(rows.stream(), "MCPU-10121");
			assertEquals(Status.Closed, row10121.getStatus());
			assertEquals(1.0, row10121.getEstimateInDays(), 0.0000001);
			assertEquals("UI", row10121.getTeam());
			
			final JiraRow row10114 = getRow(rows.stream(), "MCPU-10114");
			assertEquals(Status.InProgress, row10114.getStatus());
			assertEquals(0.75, row10114.getEstimateInDays(), 0.0000001);
			assertEquals("India", row10114.getTeam());
		}
		
	}
		
	private JiraRow getRow(final Stream<JiraRow> rows, final String key) throws Exception {
		return  rows.
				filter(j -> j.getKey().equals(key)).
				findFirst().
				orElseThrow(() -> new Exception(String.format("%s not present", key)));
	}
	
}

