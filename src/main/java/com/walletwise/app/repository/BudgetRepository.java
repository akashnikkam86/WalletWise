package com.walletwise.app.repository;

import com.walletwise.app.model.Budget;
import com.walletwise.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserAndMonthAndYear(User user, int month, int year);
}
