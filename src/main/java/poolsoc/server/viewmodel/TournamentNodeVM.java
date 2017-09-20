package poolsoc.server.viewmodel;

import poolsoc.server.entity.Match;
import poolsoc.server.entity.Player;
import poolsoc.server.entity.TournamentNode;

public abstract class TournamentNodeVM {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static TournamentNodeVM instantiate(TournamentNode tn) {
		if (tn instanceof Player) {
			return new PlayerVM((Player) tn);
		} else if (tn instanceof Match) {
			return new MatchVM((Match) tn);
		} else {
			return null;
		}
	}
}
