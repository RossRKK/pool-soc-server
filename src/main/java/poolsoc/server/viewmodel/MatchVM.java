package poolsoc.server.viewmodel;

import java.util.Date;

import poolsoc.server.entity.Match;

public class MatchVM extends TournamentNodeVM {
	
	public MatchVM (Match m) {
		super(m);
		this.contestant1 = TournamentNodeVM.instantiate(m.getContestant1());
		this.contestant2 = TournamentNodeVM.instantiate(m.getContestant2());
		this.score1 = m.getScore1();
		this.score2 = m.getScore2();
		this.scheduledTime = m.getScheduledTime();
	}
	
	private TournamentNodeVM contestant1;
	private TournamentNodeVM contestant2;
	
	private int score1;
	private int score2;
	
	private Date scheduledTime;

	public TournamentNodeVM getContestant1() {
		return contestant1;
	}

	public void setContestant1(TournamentNodeVM contestant1) {
		this.contestant1 = contestant1;
	}

	public TournamentNodeVM getContestant2() {
		return contestant2;
	}

	public void setContestant2(TournamentNodeVM contestant2) {
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
