package WebAppVulnerable.service;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
    public CorsConfigurationSource corsConfigurationSource() {
 
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", 
        "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        //Applica la configurazione a tutti i path
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(t -> t
				.requestMatchers("/home","/review").permitAll()
				.anyRequest().authenticated());
		
		http.formLogin(t -> t
				.defaultSuccessUrl("/login-success"));
		
		http.logout(t -> t
				.logoutSuccessUrl("/home"));
		
		//Abilita CORS (necessario per i link da sito evil)
		http.cors(Customizer.withDefaults());
		
		//Disabilita CSRF protection
		http.csrf(t -> t.disable());
		
		//Necessario per visualizzare console h2
		//Forse impatta XSS?
		http.headers(t -> t.frameOptions(s -> s.sameOrigin())); 
		
		return http.build();
	}
}
