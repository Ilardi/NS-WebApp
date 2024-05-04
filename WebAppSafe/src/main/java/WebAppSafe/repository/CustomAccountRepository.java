package WebAppSafe.repository;

import jakarta.transaction.Transactional;

public interface CustomAccountRepository {
	@Transactional
	public void updateAccount(String email, String address, String description,
			String username);
}
