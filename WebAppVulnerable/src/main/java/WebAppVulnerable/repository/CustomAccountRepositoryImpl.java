package WebAppVulnerable.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomAccountRepositoryImpl implements CustomAccountRepository {
	
    @PersistenceContext
    private EntityManager em;
    
    // Fix per SQL injection: query parametrica e
    // input validation nel controller
    @Override
	public void updateAccount(String email, String address, String description,
			String username) {
    	String queryString = "UPDATE Account SET email='" + email + "', "
    			+ "description='" + description + "', "
    			+ "address='" + address + "' "
    			+ "WHERE username='" + username + "'";
        em.createNativeQuery(queryString).executeUpdate();
	}
}
