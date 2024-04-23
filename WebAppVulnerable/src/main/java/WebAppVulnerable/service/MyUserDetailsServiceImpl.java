package WebAppVulnerable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import WebAppVulnerable.entity.Account;
import WebAppVulnerable.entity.MyUserDetails;
import WebAppVulnerable.repository.AccountRepository;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	@Autowired
	private AccountRepository repo;
	 
	//Questo metodo è richiesto per l'autenticazione con Spring Security
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Account account = repo.findByUsername(username);
		
		if(account == null) 
			throw new UsernameNotFoundException("Account non trovato");
		
		return new MyUserDetails(account);
	}
	
}
