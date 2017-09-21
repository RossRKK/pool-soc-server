package poolsoc.server.viewmodel;

import poolsoc.server.entity.Tournament;

public class TournamentVM {
	
	public TournamentVM(Tournament t) {
		this.id = t.getId();
		this.name = t.getName();
		this.finalMatch = TournamentNodeVM.instantiate(t.getFinalMatch());
	}
	
	private TournamentNodeVM finalMatch;
	private String name;
	private String id;
	
	/*Getters and Setters*/
	public TournamentNodeVM getFinalMatch() {
		return finalMatch;
	}
	public void setFinalMatch(TournamentNodeVM finalMatch) {
		this.finalMatch = finalMatch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
