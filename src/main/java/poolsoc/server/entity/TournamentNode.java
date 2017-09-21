package poolsoc.server.entity;

import java.util.UUID;

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
	
	public TournamentNode() {
		this.id = UUID.randomUUID().toString();
	}
	
	private String id;
	
	public abstract Player determineContestant();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
