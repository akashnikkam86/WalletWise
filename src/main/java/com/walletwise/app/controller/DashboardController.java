package com.walletwise.app.controller;

import com.walletwise.app.model.User;
import com.walletwise.app.service.ExpenseService;
import com.walletwise.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(@AuthenticationPrincipal UserDetails userDetails,
                                Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("expenses", expenseService.getUserExpenses(user));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("total", expenseService.getTotalExpenses(user));
        return "dashboard";
    }
}
