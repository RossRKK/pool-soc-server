package poolsoc.server.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poolsoc.server.entity.Tournament;
import poolsoc.server.service.TournamentService;
import poolsoc.server.viewmodel.TournamentVM;

@RestController
@RequestMapping("api/tournament")
public class TournamentController {
	TournamentService ts = new TournamentService();
	
	@RequestMapping(method = RequestMethod.GET, path ="{id}")
	public TournamentVM get(@PathVariable String id) throws IOException {
		return new TournamentVM(ts.getTournament(id));
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String set(@PathVariable String id) {
		return id;
	} 
}
