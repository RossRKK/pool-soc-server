package poolsoc.server.entity;

import java.util.HashMap;

public class Tournament {
	private TournamentNode finalMatch;
	private String name;
	private String id;
	
	/**
	 * A hashmap that contains all nodes in the tournament.
	 */
	private HashMap<String, TournamentNode> allNodes = new HashMap<String, TournamentNode>();
	
	private void indexAllNodes() {
		finalMatch.indexChildren(allNodes);
	}
	
	public TournamentNode accessNode(String nodeId) {
		return allNodes.get(nodeId);
	}
	
	public void setNode(String nodeId, TournamentNode node) throws IllegalArgumentException {
		if (allNodes.containsKey(nodeId)) {
			allNodes.put(nodeId, node);
		} else {
			throw new IllegalArgumentException("Invalid node id: " + nodeId);
		}
	}
	
	/*Getters and Setters*/
	public TournamentNode getFinalMatch() {
		return finalMatch;
	}
	public void setFinalMatch(TournamentNode finalMatch) {
		this.finalMatch = finalMatch;
		indexAllNodes();
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
