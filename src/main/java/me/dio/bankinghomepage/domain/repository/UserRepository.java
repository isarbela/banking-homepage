package me.dio.bankinghomepage.domain.repository;

import me.dio.bankinghomepage.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountNumber(String accountNumber);
    boolean existsByCardNumber(String cardNumber);
}
