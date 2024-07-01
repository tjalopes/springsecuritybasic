package com.example.springsecuritybasic.controller.restController;

import com.example.springsecuritybasic.db.model.Customer;
import com.example.springsecuritybasic.db.repository.CustomerRepository;
import com.example.springsecuritybasic.service.SpringSecurityOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@RestController
public class SpringSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityController.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SpringSecurityOperations springSecurityOperations;

    @GetMapping("/account/details")
    public String getAccountDetails(Principal principal){
        logger.info("{} - /account/details - principalName:{}", RequestMethod.GET, principal.getName());
        return "Here are the account details from the DB.";
    }

    @GetMapping("/account/balance")
    public String getAccountBalanceDetails(Principal principal){
        logger.info("{} - /account/balance - principalName:{}", RequestMethod.GET, principal.getName());
        return "Check account balance.";
    }

    @GetMapping("/account/loan")
    public String getAccountLoanData(Principal principal){
        logger.info("{} - /account/loan - principalName:{}", RequestMethod.GET, principal.getName());
        return "Check ur loans.";
    }

    @GetMapping("/account/cards")
    public String getAccountCardsDetails(Principal principal){
        logger.info("{} - /account/cards - principalName:{}", RequestMethod.GET, principal.getName());
        return "Check ur card details.";
    }

    @GetMapping("/information/contacts")
    public String getAccountContactsDetails(Principal principal){
        logger.info("{} - /information/contacts", RequestMethod.GET);
        return "Check our bank contacts.";
    }

    @GetMapping("/information/notice")
    public String getAccountNotice(Principal principal){
        logger.info("{} - /information/notice", RequestMethod.POST);
        return "Check our bank notices.";
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(Principal principal, @RequestBody Customer customer){
        logger.info("{} - /register", RequestMethod.POST);
        logger.info("{}", customer);

        springSecurityOperations.registerUser(customer);

        ResponseEntity<Void> responseEntity = ResponseEntity.created(URI.create("")).build();

        logger.info("responseEntity: " + responseEntity);

        return responseEntity;
    }
}
