package WebAppVulnerable.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomAccountRepositoryImpl implements CustomAccountRepository {
	
    @PersistenceContext
    private EntityManager em;
    
    //soluzione: usa parametrized query 
    //PREPARED STATEMENT (:email, NON ? che sono i placeholder)
    //e fai input validation (nel controller)
    @Override
	public void updateAccount(String email, String address, String description,
			String username) {
    	String queryString = "UPDATE Account SET email='" + email + "', "
    			+ "description='" + description + "', "
    			+ "address='" + address + "' "
    			+ "WHERE username='" + username + "'";
    	System.out.println(queryString);
        em.createNativeQuery(queryString).executeUpdate();
	}
}
