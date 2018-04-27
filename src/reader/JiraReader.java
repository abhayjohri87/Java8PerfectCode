package reader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import data.JiraRow;
import data.Status;

/**
 * Class to consume a BufferedReader and return a Stream of JiraRow objects.
 * 
 * @author omar
 *
 */
public class JiraReader {
	private static final String STATUS = "Status";
	private static final String ZERO = "0";
	private static final String ORIGINAL_ESTIMATE = "Original Estimate";
	private static final String TEAM = "Team";
	private static final String CLOSED = "Closed";
	private static final String ISSUE_KEY = "Issue key";

	/**
	 * Method to consume a BufferedReader and return a List of JiraRow objects.
	 * 
	 * @param reader
	 *            BufferedReader over the jira data.
	 * @return List of JiraRow objects where each JiraRow object contains
	 *         sufficient information to calculate statistics.
	 * @throws Exception
	 *             Thrown when reading from BufferedReader fails (IOException)
	 *             or no data exists.
	 */
	public List<JiraRow> read(final BufferedReader reader) throws Exception {
		final Stream<Map<String, String>> data = readData(reader);
		// TODO Complete the function. Tips
		// (1) Any status in the input data with word Closed is considered as Closed
		// (2) Estimate in the input data is in seconds. Divide it by 3600 and then by 8 to get estimate in days.
		List<JiraRow> jiraRows = new ArrayList<>();
		data.forEach(inputRow -> convertToList(inputRow, jiraRows));
		return jiraRows;
	}
	
	private void convertToList(final Map<String, String> inputRow, final List<JiraRow> jiraRows) {
		jiraRows.add(new JiraRow(
				inputRow.get(ISSUE_KEY),
				inputRow.get(STATUS).startsWith(CLOSED) ? Status.Closed : Status.InProgress, 
				inputRow.get(TEAM),
				inputRow.get(ORIGINAL_ESTIMATE).equals(ZERO) ? 0
						: Double.valueOf(inputRow.get(ORIGINAL_ESTIMATE)) / (28800))); //3600 * 8
	}

	/**
	 * Method to consume a BufferedReader over the jira data and return a Stream
	 * of Map between column headings and corresponding values from each row.
	 * 
	 * @param reader
	 *            BufferedReader over the jira data
	 * @return Stream of Map between column headings and corresponding values
	 *         from each row
	 * @throws Exception
	 *             Thrown when reading from BufferedReader fails (IOException)
	 *             or no data exists.
	 */
	private Stream<Map<String, String>> readData(final BufferedReader reader) throws Exception {
		final List<String> lines = reader.lines().collect(Collectors.toList());
		final String header = lines.stream().findFirst().orElseThrow(() -> new Exception("Empty file, no header"));
		final String[] columns = header.split(",");
		final Stream<String> linesMinusHeader = lines.stream().filter(line -> !line.equals(header));
		return linesMinusHeader.map(l -> convertToMap(columns, l.split(",")));
	}

	/**
	 * Given an array of column names and values from a row, return a Map of
	 * column name to corresponding value.
	 * 
	 * @param columns
	 *            Array of column names.
	 * @param values
	 *            Array of values from a row.
	 * @return Map of column name to corresponding value.
	 */
	private Map<String, String> convertToMap(final String[] columns, final String[] values) {
		final Map<String, String> columnToValueMap = new HashMap<>();
		for (int i = 0; i < columns.length; i++) {
			columnToValueMap.put(columns[i], values[i]);
		}

		return columnToValueMap;
	}
}
