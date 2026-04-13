package com.walletwise.app.controller;

import com.walletwise.app.model.User;
import com.walletwise.app.service.BudgetService;
import com.walletwise.app.service.ExpenseService;
import com.walletwise.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class DashboardController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/dashboard")
    public String showDashboard(@AuthenticationPrincipal UserDetails userDetails,
                                Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        BigDecimal total = expenseService.getTotalExpenses(user);
        boolean exceeded = budgetService.isBudgetExceeded(user, total, month, year);

        model.addAttribute("expenses", expenseService.getUserExpenses(user));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("total", total);
        model.addAttribute("categoryMap", expenseService.getCategoryWiseSummary(user));
        model.addAttribute("budgetExceeded", exceeded);
        budgetService.getCurrentBudget(user, month, year)
                .ifPresent(b -> model.addAttribute("budgetLimit", b.getMonthlyLimit()));

        return "dashboard";
    }
}
