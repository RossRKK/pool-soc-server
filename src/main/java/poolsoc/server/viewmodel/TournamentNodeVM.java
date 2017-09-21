package poolsoc.server.viewmodel;

import poolsoc.server.entity.Match;
import poolsoc.server.entity.Player;
import poolsoc.server.entity.TournamentNode;

public abstract class TournamentNodeVM {
	
	private String id;
	
	TournamentNodeVM(TournamentNode tn) {
		this.id = tn.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
