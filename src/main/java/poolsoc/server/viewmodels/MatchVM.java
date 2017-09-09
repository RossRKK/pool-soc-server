package poolsoc.server.viewmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import poolsoc.server.interfaces.IPlayer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchVM implements IPlayer {
	private PlayerVM winner;
	
	private IPlayer player1;
	private IPlayer player2;
	
	public MatchVM() {}
	
	public IPlayer getPlayer1() {
		return player1;
	}
	
	private int score1;
	private int score2;
	
	public PlayerVM getPlayer() {
		return winner;
	}
	
	public void setPlayer1(IPlayer player1) {
		this.player1 = player1;
	}

	public IPlayer getPlayer2() {
		return player2;
	}

	public void setPlayer2(IPlayer player2) {
		this.player2 = player2;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public PlayerVM getWinner() {
		return winner;
	}

	public void setWinner(PlayerVM winner) {
		this.winner = winner;
	}
}
