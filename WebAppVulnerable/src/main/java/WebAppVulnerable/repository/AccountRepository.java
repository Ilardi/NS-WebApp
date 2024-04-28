package WebAppVulnerable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import WebAppVulnerable.entity.Account;

public interface AccountRepository 
extends JpaRepository<Account, String>, CustomAccountRepository {
	Account findByUsername(String username);
}
