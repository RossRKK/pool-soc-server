package poolsoc.server.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import poolsoc.server.entity.Tournament;
import poolsoc.server.service.TournamentService;
import poolsoc.server.viewmodel.TournamentVM;

@RestController
@RequestMapping("api/tournament")
public class TournamentController {
	TournamentService ts = new TournamentService();
	
	@RequestMapping(method = RequestMethod.GET, path = "")
	public List<TournamentVM> getAll() {
		return ts.getAll().map(t -> new TournamentVM(t)).collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.GET, path ="{id}")
	public TournamentVM get(@PathVariable String id) throws IOException {
		return new TournamentVM(ts.getTournament(id));
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String set(@PathVariable String id) {
		return id;
	}
	
	@RequestMapping(path = "create")
	public TournamentVM generate(@RequestBody List<String> draw, String name, boolean redraw) throws JsonGenerationException, JsonMappingException, IOException {
		Tournament t = ts.generateTorunament(draw, redraw);
		
		t.setName(name);
		t.setId(UUID.randomUUID().toString());
		
		ts.saveTournament(t);
		
		return new TournamentVM(t);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path ="{tournamentId}/{nodeId}")
	public TournamentVM updateScore(@PathVariable String tournamentId, @PathVariable String nodeId, int score1, int score2) throws IOException {
		ts.updateMatchScore(tournamentId, nodeId, score1, score2);
		return new TournamentVM(ts.getTournament(tournamentId));
	}
}
