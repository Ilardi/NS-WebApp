package WebAppVulnerable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import WebAppVulnerable.entity.Account;
import WebAppVulnerable.repository.AccountRepository;

@Controller
public class WebController {

    @Autowired
    private AccountRepository accountRepository;
	
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/review")
	public String review() {
		return "review";
	}
	
	//Questo metodo prende la recensione scritta dall'utente e 
	//costruisce un html per mostrargliela (reflected XSS).
    @PostMapping("/review")
    public String submitReview(Model model, @RequestParam String review) {
    	
        String prefix = "<h1>La tua recensione:</h1>";
        model.addAttribute("prefix", prefix);
        
        //Questo per il fix
        //review = HtmlUtils.htmlEscape(review);
        //Oppure usa th:text nel file html
        //al posto di th:utext
        model.addAttribute("review", review);

        return "review";
    }
	
	@GetMapping("/home")
	public String home(Model model) {
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Account account = accountRepository.findByUsername(authentication.getName());
    	
    	model.addAttribute("username", account.getUsername());
    	model.addAttribute("balance", account.getBalance());
    	model.addAttribute("email", account.getEmail());
    	model.addAttribute("address", account.getAddress());
    	model.addAttribute("description", account.getDescription());
    	
		return "home";
	}
	
	@GetMapping("/transfer")
	public String getTransferForm() {
		return "transfer";
	}
	
    @PostMapping("/transfer")
    @ResponseBody
    public String transfer(@RequestParam("destinationUsername") String destinationUsername,@RequestParam("amount") int amount) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Account sourceAccount = accountRepository.findByUsername(authentication.getName());
    	Account destinationAccount = accountRepository.findByUsername(destinationUsername);

        if (sourceAccount == null || destinationAccount == null) {
            return "L'account selezionato non esiste";
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
    @ResponseBody
    public Integer getBalance() {
    
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Account sourceAccount = accountRepository.findByUsername(authentication.getName());
    	
    	return sourceAccount.getBalance();
    }
	
	@GetMapping("/update-account")
	public String getUpdateAccountForm() {
		return "updateAccount";
	}
	
    @PostMapping("/update-account")
    @ResponseBody
    public String updateAccount(
    		@RequestParam("email") String email, 
    		@RequestParam("address") String address, 
    		@RequestParam("description") String description) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	
    	accountRepository.updateAccount(email, address, description, username);
        
        return "Account modificato con successo";
    }
	
}
