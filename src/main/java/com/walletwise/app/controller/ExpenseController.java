package com.walletwise.app.controller;

import com.walletwise.app.model.Expense;
import com.walletwise.app.model.User;
import com.walletwise.app.service.ExpenseService;
import com.walletwise.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String showAddExpensePage() {
        return "add-expense";
    }

    @PostMapping("/add")
    public String addExpense(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam String title,
                             @RequestParam BigDecimal amount,
                             @RequestParam String category,
                             @RequestParam LocalDate expenseDate,
                             @RequestParam(required = false) String note) {
        User user = userService.findByUsername(userDetails.getUsername());
        expenseService.addExpense(user, title, amount, category, expenseDate, note);
        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "edit-expense";
    }

    @PostMapping("/edit/{id}")
    public String updateExpense(@PathVariable Long id,
                                @RequestParam String title,
                                @RequestParam BigDecimal amount,
                                @RequestParam String category,
                                @RequestParam LocalDate expenseDate,
                                @RequestParam(required = false) String note) {
        expenseService.updateExpense(id, title, amount, category, expenseDate, note);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/dashboard";
    }
}
