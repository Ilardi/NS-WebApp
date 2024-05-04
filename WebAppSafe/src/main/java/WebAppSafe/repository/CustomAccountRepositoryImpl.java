package WebAppSafe.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class CustomAccountRepositoryImpl implements CustomAccountRepository {
	
    @PersistenceContext
    private EntityManager em;
    
    // Soluzione a SQL Injection: uso di query parametrica JQPL
    // Inoltre si può fare input validation nel controller
    // (non implementato per mostrare l'effetto della query parametrica)
    @Override
    public void updateAccount(String email, String address, String description, String username) {
        String queryString = "UPDATE Account SET email = :email, description = :description, "
        		+ "address = :address WHERE username = :username";
        Query query = em.createQuery(queryString);
        query.setParameter("email", email);
        query.setParameter("description", description);
        query.setParameter("address", address);
        query.setParameter("username", username);
        query.executeUpdate();
    }

}
