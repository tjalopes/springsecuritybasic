package com.example.springsecuritybasic.controller.restController;

import com.example.springsecuritybasic.db.model.*;
import com.example.springsecuritybasic.db.repository.CustomerRepository;
import com.example.springsecuritybasic.llayer.SpringSecurityOperations;
import com.example.springsecuritybasic.resource.springSecurityAPIResource.input.PostContactResource;
import com.example.springsecuritybasic.resource.springSecurityAPIResource.input.PostUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class SpringSecurityAPIController {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityAPIController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SpringSecurityOperations springSecurityOperations;

    @PostMapping("/register")
    public ResponseEntity<String> postUser(@RequestBody PostUserResource postUserResource){
        logger.info("{} - /register", RequestMethod.POST);
        logger.info("{}", postUserResource);

        springSecurityOperations.postUser(postUserResource);

        ResponseEntity<String> responseEntity = ResponseEntity.created(URI.create("")).body("Given User Details are successfully saved.");

        logger.info("responseEntity: " + responseEntity);

        return responseEntity;
    }

    @GetMapping("/user")
    public ResponseEntity<Customer> getUserDetailsAfterLogin(Authentication authentication) {
        logger.info("{} - /user", RequestMethod.GET);

        Customer customer = springSecurityOperations.getUserDetailsAfterLogin(authentication);

        ResponseEntity<Customer> responseEntity = ResponseEntity.ok(customer);

        logger.info("responseEntity: " + responseEntity);

        return responseEntity;
    }

    @GetMapping("/account/details/{id}")
    public ResponseEntity<Account> getAccountDetails(Principal principal, @PathVariable int id){
        logger.info("{} - /account/details/{} - principalName:{}", RequestMethod.GET, id, principal.getName());

        Account account = springSecurityOperations.getAccountDetails(id);

        ResponseEntity<Account> responseEntity = ResponseEntity.ok().body(account);

        logger.info("{}", responseEntity);

        return responseEntity;
    }

    @GetMapping("/account/balance/{id}")
    public ResponseEntity<List<AccountTransaction>> getAccountBalanceDetails(Principal principal, @PathVariable int id){
        logger.info("{} - /account/balance/{} - principalName:{}", RequestMethod.GET, id, principal.getName());

        List<AccountTransaction> accountTransactionList = springSecurityOperations.getAccountBalance(id);

        ResponseEntity<List<AccountTransaction>> responseEntity = ResponseEntity.ok().body(accountTransactionList);

        logger.info("{}", responseEntity);

        return responseEntity;
    }

    @GetMapping("/account/loan/{id}")
    public ResponseEntity<List<Loan>> getAccountLoanData(Principal principal, @PathVariable int id){
        logger.info("{} - /account/loan/{} - principalName:{}", RequestMethod.GET, id, principal.getName());

        List<Loan> loanList = springSecurityOperations.getAccountLoans(id);

        ResponseEntity<List<Loan>> responseEntity = ResponseEntity.ok().body(loanList);

        logger.info("{}", responseEntity);

        return responseEntity;
    }

    @GetMapping("/account/cards/{id}")
    public ResponseEntity<List<Cards>> getAccountCardsDetails(Principal principal, @PathVariable int id){
        logger.info("{} - /account/cards/{} - principalName:{}", RequestMethod.GET, id, principal.getName());

        List<Cards> cardsList = springSecurityOperations.getCardsDetails(id);

        ResponseEntity<List<Cards>> responseEntity = ResponseEntity.ok().body(cardsList);

        logger.info("{}", responseEntity);

        return responseEntity;
    }

    @PostMapping("/information/contact")
    @PreAuthorize("#postContactResource.contactName != 'TEST'")
    public ResponseEntity<List<Contact>> getAccountContactsDetails(@RequestBody PostContactResource postContactResource){
        logger.info("{} - /information/contacts", RequestMethod.POST);
        logger.info("{}", postContactResource);

        List<Contact> contactList = springSecurityOperations.postContact(postContactResource);

        ResponseEntity<List<Contact>> responseEntity = ResponseEntity.created(URI.create("")).body(contactList);

        logger.info("{}", responseEntity);

        return responseEntity;
    }

    @GetMapping("/information/notice")
    public ResponseEntity<List<Notice>> getAccountNotice(){
        logger.info("{} - /information/notice", RequestMethod.GET);

        List<Notice> noticeList = springSecurityOperations.getNotices();

        ResponseEntity<List<Notice>> responseEntity = ResponseEntity.ok(noticeList);

        logger.info("{}", responseEntity);

        return responseEntity;
    }
}
