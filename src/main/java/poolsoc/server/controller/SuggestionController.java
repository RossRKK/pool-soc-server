package poolsoc.server.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/suggestion")
public class SuggestionController {
	
	@RequestMapping(method = RequestMethod.POST, path = "")
	public void postSuggestion(String subject, String body) throws IOException {
		
		//log the suggestion to a file
		File folder = new File("suggestions");
		folder.mkdirs();
		
		FileWriter out = new FileWriter("suggestions/" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		
		out.write(subject + "\n\n");
		out.write(body);
		
		out.flush();
		out.close();
	}
}
