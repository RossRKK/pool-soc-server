package poolsoc.server.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import poolsoc.server.entity.Tournament;

public class TournamentService {
	//keep a buffer of tournaments in memory
	HashMap<String, Tournament> tournaments = new HashMap<String, Tournament>();
	
	//get a tournament from its id
	public Tournament getTournament(String id) throws IOException {
		if (tournaments.containsKey(id)) {
			return tournaments.get(id);
		} else {
			Tournament t = loadTournament(id);
			tournaments.put(id, t);
			return t;
		}
	}
	
	public Tournament loadTournament(String id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(id + ".json");
        
        System.out.println(file.getAbsolutePath());
        
    	Tournament t = objectMapper.readValue(file, Tournament.class);

        return t;
	}
}
