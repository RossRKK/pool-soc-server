package poolsoc.server.entity;

import java.util.Date;

public class Match extends TournamentNode {
	private TournamentNode contestant1;
	private TournamentNode contestant2;
	
	private int score1;
	private int score2;
	
	private Date scheduledTime;
	
	@Override
	public Player determineContestant() {
		//return the winner
		if (score1 > score2) {
			return contestant1.determineContestant();
		} else if (score2 > score1) {
			return contestant2.determineContestant();
		} else {		
			return null;
		}
	}
	
	/*Getters and Setters*/

	public TournamentNode getContestant1() {
		return contestant1;
	}

	public void setContestant1(TournamentNode contestant1) {
		this.contestant1 = contestant1;
	}

	public TournamentNode getContestant2() {
		return contestant2;
	}

	public void setContestant2(TournamentNode contestant2) {
		this.contestant2 = contestant2;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
}
