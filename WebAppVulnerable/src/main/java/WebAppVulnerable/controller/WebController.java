package WebAppVulnerable.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import WebAppVulnerable.entity.Account;
import WebAppVulnerable.repository.AccountRepository;

@RestController
public class WebController {

    @Autowired
    private AccountRepository accountRepository;
	
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
	
    @PostMapping("/transfer")
    public String transferMoney(@RequestParam("destinationUsername") String destinationUsername,@RequestParam("amount") int amount) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Account sourceAccount = accountRepository.findByUsername(authentication.getName());
    	Account destinationAccount = accountRepository.findByUsername(destinationUsername);

        if (sourceAccount == null || destinationAccount == null) {
            return "Uno o entrambi gli account non esistono";
        }
        
        if(sourceAccount == destinationAccount)
        	return "Scegliere un account destinazione diverso";

        if (sourceAccount.getBalance() < amount) {
            return "Saldo insufficiente per il trasferimento";
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        return "Trasferimento completato con successo";
    }
    
    @GetMapping("/balance")
    public Integer getBalance() {
    
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Account sourceAccount = accountRepository.findByUsername(authentication.getName());
    	
    	return sourceAccount.getBalance();
    }
    
	@GetMapping("/money_transfer")
	public String FormMoneyTransfer() {
		return readHtml("templates/Money_Transfer.html");
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
