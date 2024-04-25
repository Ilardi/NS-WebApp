package WebAppVulnerable.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/home")
	public String home() {
		return "Home page";
	}
	
	@GetMapping("/review")
	public String review() {
		return readHtml("templates/review.html");
	}
	
	//Questo metodo prende la recensione scritta dall'utente e 
	//costruisce un html per mostrargliela (reflected XSS).
    @PostMapping("/review")
    public String submitReview(@RequestParam("review") String review) {
    	
        String file = readHtml("templates/review.html");
        String prefix = "<h1>La tua recensione:</h1><br>";
        
        //Questo per il fix
        //review = HtmlUtils.htmlEscape(review);
        
        return file.replace("<!-- {reviewText} -->", prefix + review);
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
