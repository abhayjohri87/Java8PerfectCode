package testdata;

import java.util.Arrays;
import java.util.stream.Stream;

import data.Effort;
import data.TeamSummary;

public class Data {
	public static String raw = "Issue key,Issue id,Parent id,Summary,Status,Issue Type,Original Estimate,Priority,Team\n" + 
			"MCPU-10140,2748508,2660783,QA Design- Notify Parties Assignment Confirmed,Open,Sub-task,28800,Medium,London\n" + 
			"MCPU-10139,2748506,2729720,QA Acceptance - Submit and Match Assignment,Closed - Complete,Sub-task,1800,Medium,London\n" + 
			"MCPU-10137,2748486,2660772,QA Acceptance - Submit and Mismatch Assignment,Open,Sub-task,1800,Medium,London\n" + 
			"MCPU-10134,2748379,2667218,Exercise Submission,Open,Sub-task,43200,Medium,UI\n" + 
			"MCPU-10133,2748378,2667218,Provide payload,Open,Sub-task,14400,Medium,UI\n" + 
			"MCPU-10132,2748340,2735214,QA Design - Manage Disable via FpML,Closed - Complete,Sub-task,57600,Medium,London\n" + 
			"MCPU-10122,2748283,2671006,Fetch and Display Suggested matching candidates,Open,Sub-task,57600,Medium,UI\n" + 
			"MCPU-10121,2748282,2671003,Show best match percentage n Grid - E2E Verification,Closed - Complete,Sub-task,28800,Medium,UI\n" + 
			"MCPU-10120,2748281,2671271,Compare Matching fields on UI,Open,Sub-task,43200,Medium,UI\n" + 
			"MCPU-10118,2748276,2671277,Partial Allege Submission,Open,Sub-task,28800,Medium,UI\n" + 
			"MCPU-10117,2748275,2671277,Full Allege Submission,Open,Sub-task,43200,Medium,UI\n" + 
			"MCPU-10116,2748269,2735217,Upgrade normalised & serialization version to support Disable event in RegServ,Open,Sub-task,14400,Medium,London\n" + 
			"MCPU-10115,2748240,2746173,QA Execution - Submit Regulatory Reporting fields via MQ - Assignment - OR submission,Open,Sub-task,28800,Medium,India\n" + 
			"MCPU-10114,2748239,2746173,QA Implementation - Submit Regulatory Reporting fields via MQ - Assignment - OR submission,Open,Sub-task,21600,Medium,India\n" + 
			"MCPU-10113,2748238,2746173,QA Design - Submit Regulatory Reporting fields via MQ - Assignment - OR submission,Open,Sub-task,7200,Medium,India\n" + 
			"MCPU-10112,2748237,2746174,QA Peer Review - Submit Regulatory Reporting fields via MQ - Assignment - EE submission,Open,Sub-task,3600,Medium,India\n" + 
			"MCPU-10111,2748236,2746174,QA Execution - Submit Regulatory Reporting fields via MQ - Assignment - EE submission,Open,Sub-task,28800,Medium,India\n" + 
			"MCPU-10110,2748235,2746174,QA Implementation - Submit Regulatory Reporting fields via MQ - Assignment - EE submission,Open,Sub-task,21600,Medium,India\n"; 

	public static Stream<TeamSummary> result = Arrays.asList(
			new TeamSummary("London", new Effort(3.625, 1.5625)),
			new TeamSummary("UI", new Effort(9.0, 8.0)),
			new TeamSummary("India", new Effort(3.875, 3.875))).stream();
}
