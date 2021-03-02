package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {}