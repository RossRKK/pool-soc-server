package poolsoc.server.entity;

public class Player extends TournamentNode {
	private String name;
	
	public Player() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Player determineContestant() {
		return this;
	}
}
