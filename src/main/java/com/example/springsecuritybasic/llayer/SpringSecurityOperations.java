package com.example.springsecuritybasic.llayer;

import com.example.springsecuritybasic.db.model.*;
import com.example.springsecuritybasic.db.repository.*;
import com.example.springsecuritybasic.resource.springSecurityAPIResource.input.PostContactResource;
import com.example.springsecuritybasic.resource.springSecurityAPIResource.input.PostUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SpringSecurityOperations {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityOperations.class);


    public void postUser(@RequestBody PostUserResource postUserResource){
        try {
            Customer customer = new Customer(postUserResource);
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));

            customerRepository.save(customer);
        } catch (Exception e){
            throw new InternalError(e.getMessage());
        }
    }

    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customerList = customerRepository.findByEmail(authentication.getName());
        if(!customerList.isEmpty()) {
            return customerList.get(0);
        } else {
            return null;
        }
    }

    public Account getAccountDetails(int id) {
        Account account = accountRepository.findByCustomerId(id);
        if (account != null) {
            return account;
        } else {
            return null;
        }
    }

    public List<AccountTransaction> getAccountBalance(int id) {
        List<AccountTransaction> accountTransactionList = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        if (accountTransactionList != null) {
            return accountTransactionList;
        } else {
            return null;
        }
    }

    public List<Cards> getCardsDetails(int id) {
        List<Cards> cardsList = cardsRepository.findByCustomerId(id);
        if (cardsList != null) {
            return cardsList;
        } else {
            return null;
        }
    }

    public List<Loan> getAccountLoans(int id) {
        // Retrieve the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Extract and log user roles
            String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
            logger.info("Current user roles: {}", roles);
        } else {
            logger.info("No authenticated user found or user is not authenticated.");
        }

        logger.info("DBService.getAccountLoans");
        List<Loan> loanList = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        logger.info("loanRepository.findByCustomerIdOrderByStartDtDesc");
        if(loanList != null) {
            return loanList;
        } else {
            return null;
        }
    }

    public List<Notice> getNotices() {
        List<Notice> noticeList = noticeRepository.findAllActiveNotices();
        if (noticeList!= null) {
            return noticeList;
        } else {
            return null;
        }
    }

    public List<Contact> postContact(PostContactResource postContactResource) {
        Contact contact = new Contact(postContactResource);
        contact.setContactId(getServiceReqNumber());
        contactRepository.save(contact);
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        return contactList;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR"+ranNum;
    }
}
