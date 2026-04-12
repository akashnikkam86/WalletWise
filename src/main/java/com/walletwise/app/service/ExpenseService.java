package com.walletwise.app.service;

import com.walletwise.app.model.Expense;
import com.walletwise.app.model.User;
import com.walletwise.app.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(User user, String title, BigDecimal amount,
                              String category, LocalDate expenseDate, String note) {
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setExpenseDate(expenseDate);
        expense.setNote(note);
        expense.setCreatedAt(LocalDateTime.now());
        return expenseRepository.save(expense);
    }

    public List<Expense> getUserExpenses(User user) {
        return expenseRepository.findByUserOrderByExpenseDateDesc(user);
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public Expense updateExpense(Long id, String title, BigDecimal amount,
                                 String category, LocalDate expenseDate, String note) {
        Expense expense = getExpenseById(id);
        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setExpenseDate(expenseDate);
        expense.setNote(note);
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public BigDecimal getTotalExpenses(User user) {
        return getUserExpenses(user).stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
