package com.walletwise.app.controller;

import com.walletwise.app.model.User;
import com.walletwise.app.service.BudgetService;
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
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showBudgetPage(@AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        budgetService.getCurrentBudget(user, month, year)
                .ifPresent(b -> model.addAttribute("currentBudget", b.getMonthlyLimit()));
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "budget";
    }

    @PostMapping
    public String setBudget(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam BigDecimal monthlyLimit) {
        User user = userService.findByUsername(userDetails.getUsername());
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        budgetService.setBudget(user, monthlyLimit, month, year);
        return "redirect:/dashboard";
    }
}
