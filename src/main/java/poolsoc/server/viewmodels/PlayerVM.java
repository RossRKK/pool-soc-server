package poolsoc.server.viewmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import poolsoc.server.interfaces.IPlayer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerVM implements IPlayer {
	private String name;
	
	public PlayerVM() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public PlayerVM getPlayer() {
		return this;
	}
}
