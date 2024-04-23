package WebAppVulnerable.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/home")
	public String home() {
		return "Home page";
	}
	
	@GetMapping("/login-success")
	public String loginSuccess() {
		return readHtml("templates/loginSuccess.html");
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
