package WebAppVulnerable.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailsService extends UserDetailsService {

	//Questo metodo è richiesto per l'autenticazione con Spring Security
	UserDetails loadUserByUsername(String userName);
	
	//Qui si possono aggiungere altri metodi di utilità per operazioni
	//sugli account (qua solo le dichiarazioni, le implementazioni
	//nell'altro file)

}