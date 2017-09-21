package poolsoc.server.viewmodel;

import poolsoc.server.entity.Player;

public class PlayerVM extends TournamentNodeVM {
	private String name;

	public PlayerVM(Player p) {
		super(p);
		this.name = p.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
