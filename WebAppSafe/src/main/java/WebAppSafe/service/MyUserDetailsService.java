package WebAppSafe.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailsService extends UserDetailsService {

	//Questo metodo è richiesto per l'autenticazione con Spring Security
	UserDetails loadUserByUsername(String userName);

}