package poolsoc.server.entity;

import java.util.HashMap;

public class Tournament {
	private TournamentNode finalMatch;
	private String name;
	private String id;
	
	private HashMap<Integer, TournamentNode> allNodes = new HashMap<Integer, TournamentNode>();
	
	public TournamentNode accessNode(int nodeId) {
		return allNodes.get(new Integer(nodeId));
	}
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
