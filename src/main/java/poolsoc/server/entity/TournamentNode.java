package poolsoc.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Player.class, name = "player"),

    @JsonSubTypes.Type(value = Match.class, name = "match") }
)
public abstract class TournamentNode {
	private static int nextId = 0;
	private static int nextId() {
		nextId ++;
		return nextId;
	}
	
	public TournamentNode() {
		this.id = nextId();
	}
	
	private int id;
	
	public abstract Player determineContestant();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
