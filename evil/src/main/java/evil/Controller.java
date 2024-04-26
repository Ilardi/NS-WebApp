package evil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String evilHome() {
		return readHtml("templates/evilHome.html");
	}
	
	@GetMapping("/CSRF_Attack")
	public String CSRF_HOME() {
		return readHtml("templates/CSRF.html");
	}
	
	private String readHtml(String path) {
		
		String content = "";
		
		try {
			InputStream resource = new ClassPathResource(path).getInputStream();
			content = new String(resource.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
}
