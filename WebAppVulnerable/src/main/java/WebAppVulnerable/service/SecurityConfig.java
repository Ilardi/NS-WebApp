package WebAppVulnerable.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(t -> t
				.requestMatchers("/home").permitAll()
				.anyRequest().authenticated());
		
		http.formLogin(t -> t
				.defaultSuccessUrl("/login-success"));
		
		http.logout(t -> t
				.logoutSuccessUrl("/home"));
		
		//Disabilita CSRF protection
		http.csrf(t -> t.disable());
		
		//Necessario per visualizzare console h2
		//Forse impatta XSS?
		http.headers(t -> t.frameOptions(s -> s.sameOrigin())); 
		
		return http.build();
	}
}
