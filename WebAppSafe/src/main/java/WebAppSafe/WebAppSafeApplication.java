package WebAppSafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import WebAppSafe.entity.Account;
import WebAppSafe.entity.Comment;
import WebAppSafe.repository.AccountRepository;
import WebAppSafe.repository.CommentRepository;

@SpringBootApplication
@EnableJpaRepositories("WebAppSafe.repository")
@EntityScan("WebAppSafe.entity") 
@ComponentScan({"WebAppSafe"})
public class WebAppSafeApplication {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppSafeApplication.class, args);
	}

	@Bean
	public String init() {
		Account a1 = new Account("alice",encoder.encode("alicepass"),1000,
				"alice@demo.com","Via Roma 42","Ciao sono Alice!");
		Account a2 = new Account("bob",encoder.encode("bobpass"),2000,
				"bob@demo.com","Via Napoli 15","Ciao sono Bob!");
		Account a3 = new Account("carl",encoder.encode("carlpass"),3000,
				"carl@demo.com","Via Trieste 7","Ciao sono Carl!");
		
		accountRepo.save(a1);
		accountRepo.save(a2);
		accountRepo.save(a3);
		
		Comment c1 = new Comment((long) 1,"alice","Ciao a tutti!");
		Comment c2 = new Comment((long) 2,"bob","Mandatemi dei soldi :(");
		Comment c3 = new Comment((long) 3,"carl","Bel tempo oggi.");
		
		commentRepo.save(c1);
		commentRepo.save(c2);
		commentRepo.save(c3);
		
		return "";
	}
}
