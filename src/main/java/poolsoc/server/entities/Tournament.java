package poolsoc.server.entities;

public class Tournament {
	private TournamentNode finalMatch;
	private String name;
	
	/*Getters and Setters*/
	public TournamentNode getFinalMatch() {
		return finalMatch;
	}
	public void setFinalMatch(TournamentNode finalMatch) {
		this.finalMatch = finalMatch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
