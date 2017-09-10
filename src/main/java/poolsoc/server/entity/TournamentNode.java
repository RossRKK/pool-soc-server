package poolsoc.server.entity;

public abstract class TournamentNode {
	protected Player contestant;
	
	public Player getContestant() {
		return contestant;
	}

	public void setContestant(Player contestant) {
		this.contestant = contestant;
	}
}
