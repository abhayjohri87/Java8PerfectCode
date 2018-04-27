package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;

import processor.JiraProcessor;
import reader.JiraReader;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Read the file subtasks-0504.csv in the data folder and produce
		// the summary of total effort and effort remaining of each team.
		final JiraReader jiraReader = new JiraReader();
		final JiraProcessor jiraProcessor = new JiraProcessor();
		try (final BufferedReader bufferedReader = new BufferedReader(new FileReader("data/subtasks-0504.csv"))) {
			jiraProcessor.process(jiraReader.read(bufferedReader))
					.forEach(e -> System.out
							.println("Team Name = " + e.getTeam() + ", Total Effort = " + e.getEffort().getTotalEffort()
									+ ", Remaining Effort = " + e.getEffort().getRemainingEffort()));
			;
		}

	}

}
