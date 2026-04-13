package com.walletwise.app.service;

import com.walletwise.app.model.Budget;
import com.walletwise.app.model.User;
import com.walletwise.app.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget setBudget(User user, BigDecimal monthlyLimit, int month, int year) {
        Optional<Budget> existing = budgetRepository.findByUserAndMonthAndYear(user, month, year);
        Budget budget = existing.orElse(new Budget());
        budget.setUser(user);
        budget.setMonthlyLimit(monthlyLimit);
        budget.setMonth(month);
        budget.setYear(year);
        if (budget.getCreatedAt() == null) {
            budget.setCreatedAt(LocalDateTime.now());
        }
        return budgetRepository.save(budget);
    }

    public Optional<Budget> getCurrentBudget(User user, int month, int year) {
        return budgetRepository.findByUserAndMonthAndYear(user, month, year);
    }

    public boolean isBudgetExceeded(User user, BigDecimal totalSpent, int month, int year) {
        return getCurrentBudget(user, month, year)
                .map(b -> totalSpent.compareTo(b.getMonthlyLimit()) > 0)
                .orElse(false);
    }
}
