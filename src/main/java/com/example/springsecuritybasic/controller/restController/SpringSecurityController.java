package com.example.springsecuritybasic.controller.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

    @GetMapping("/welcome")
    public String sayWelcome(){
        return "Welcome to String Application with Security!";
    }

    @GetMapping("/account")
    public String getAccountDetails(){
        return "Here are the account details from the DB.";
    }

    @GetMapping("/account/balance")
    public String getAccountBalanceDetails(){
        return "Check account balance.";
    }

    @GetMapping("/account/loan")
    public String getAccountLoanData(){
        return "Check ur loans.";
    }

    @GetMapping("/account/cards")
    public String getAccountCardsDetails(){
        return "Check ur card details.";
    }

    @GetMapping("/account/contacts")
    public String getAccountContactsDetails(){
        return "Check ur card contacts.";
    }

    @GetMapping("/account/notice")
    public String getAccountNotice(){
        return "Check ur account notices.";
    }
}
