package poolsoc.server.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import poolsoc.server.entity.Match;
import poolsoc.server.entity.Player;
import poolsoc.server.entity.Tournament;
import poolsoc.server.entity.TournamentNode;

public class TournamentService {
	private final String PATH = "tournaments";
	
	//keep a buffer of tournaments in memory
	HashMap<String, Tournament> tournaments = new HashMap<String, Tournament>();
	
	/**
	 * Get a list of all available tournaments.
	 * @return The list of all available tournaments
	 */
	public Stream<Tournament> getAll() {
		File dir = new File(PATH);
		
		List<String> ids = Arrays.asList(dir.list());
		//map the 
		Stream<Tournament> t = ids.parallelStream().map(id ->  getTournament(id));
		
		return t;
	}
	
	//get a tournament from its id
	public Tournament getTournament(String id) {
		if (tournaments.containsKey(id)) {
			return tournaments.get(id);
		} else {
			Tournament t;
			try {
				t = loadTournament(id);
				
			} catch (IOException e) {
				t = null;
			}
			tournaments.put(id, t);
			return t;
		}
	}
	
	private File getTorunamentFile(String id) throws IOException {
		File directory = new File(PATH);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
		File file = new File(PATH + File.separator + id);
		
		if (!file.exists()) {
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
		}
		
		return file;
	}
	
	/**
	 * Read a tournament from the disk.
	 * @param id The id of the tournament
	 * @return The tournament object
	 * @throws IOException
	 */
	private Tournament loadTournament(String id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
    	Tournament t = objectMapper.readValue(getTorunamentFile(id), Tournament.class);
    	
        return t;
	}
	
	/**
	 * Save this tournament to the disk
	 * @param t The tournament object to be saved
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void saveTournament(Tournament t) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		File file = getTorunamentFile(t.getId());
		
		objectMapper.writeValue(file, t);
	}
	
	/**
	 * Generate a tournament object given the draw
	 * @param draw The draw of the tournament
	 * @param randomise Randomise the draw
	 * @return The generated tournament object
	 */
	public Tournament generateTorunament(List<String> draw, boolean randomise) {
		if (randomise) {
			Collections.shuffle(draw);
		}
		
		Player[] players = new Player[draw.size()];
		
		for (int i = 0; i < draw.size(); i++) {
			players[i] = new Player();
			players[i].setName(draw.get(i));
			players[i].generateId();
		}

		int firstRoundSize = Integer.highestOneBit(players.length);
		
		//the number of players that will be in the prelim round
		int prelimSize = (players.length - firstRoundSize) * 2;
		
		//the first round
		LinkedList<TournamentNode> firstRound = new LinkedList<TournamentNode>();
		
		//set up matches for the prelims
		for (int i = 0; i < prelimSize; i += 2) {
			Match m = new Match();
			
			m.setContestant1(players[i]);
			m.setContestant2(players[i + 1]);
			m.generateId();
			
			firstRound.add(m);
		}
		
		//add the remaining players
		for (int i = prelimSize; i < players.length; i++) {
			firstRound.add(players[i]);
		}
		
		//create new rounds until we're done
		List<TournamentNode> currentRound = firstRound;
		while (currentRound.size() > 1) {
			List<TournamentNode> newRound = new LinkedList<TournamentNode>();
			
			for (int i = 0; i < currentRound.size(); i += 2) {
				Match m = new Match();
				
				m.setContestant1(currentRound.get(i));
				m.setContestant2(currentRound.get(i + 1));
				m.generateId();
				
				newRound.add(m);
			}
			
			currentRound = newRound;
		}
		
		Tournament t = new Tournament();
		t.setFinalMatch(currentRound.get(0));
		
		return t;
	}
	
	/* Stuff for updating tournament nodes */
	
	/**
	 * Update the score of a match
	 * @param tournamentId The id of the tournament the match is in
	 * @param nodeId The id of the node the match is
	 * @param score1 The new score 1 (-1 if it shouldn't be changed)
	 * @param score2 The new score 2 (-1 if it shouldn't be changed)
	 * @return The updated tournament node
	 * @throws IOException
	 */
	public TournamentNode updateMatchScore(String tournamentId, String nodeId, int score1, int score2) throws IOException {
		Tournament t = getTournament(tournamentId);
		TournamentNode match = t.accessNode(nodeId);
		if (match instanceof Match) {
			if (score1 >= 0) {
				((Match)match).setScore1(score1);
			}
			if (score2 >= 0) {			
				((Match)match).setScore2(score2);
			}
		} else {
			System.out.println("Error," + nodeId + " (" + match + ") isn't a match in tournament " + tournamentId);
		}
		
		saveTournament(t);
		
		return match;
	}
}
