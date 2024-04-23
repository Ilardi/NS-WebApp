package WebAppVulnerable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import WebAppVulnerable.repository.AccountRepository;
import WebAppVulnerable.entity.Account;

@SpringBootApplication
@EnableJpaRepositories("WebAppVulnerable.repository")
@EntityScan("WebAppVulnerable.entity") 
@ComponentScan({"WebAppVulnerable"})
public class WebAppVulnerableApplication {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AccountRepository accountRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppVulnerableApplication.class, args);
	}

	//Questo metodo aggiunge degli utenti allo start.
	//Si potrebbero anche aggiungere dei commenti nella rispettiva
	//repository se necessario
	@Bean
	public String init() {
		Account a1 = new Account("alice",encoder.encode("alicepass"),1000);
		Account a2 = new Account("bob",encoder.encode("bobpass"),2000);
		Account a3 = new Account("carl",encoder.encode("carlpass"),3000);
		
		accountRepo.save(a1);
		accountRepo.save(a2);
		accountRepo.save(a3);
		
		return "";
	}
}
