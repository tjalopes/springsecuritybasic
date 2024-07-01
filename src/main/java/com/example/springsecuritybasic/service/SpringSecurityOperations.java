package com.example.springsecuritybasic.service;

import com.example.springsecuritybasic.db.model.Customer;
import com.example.springsecuritybasic.db.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SpringSecurityOperations {

    @Autowired
    CustomerRepository customerRepository;

    public void registerUser(@RequestBody Customer customer){
        try {
            Customer savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() <= 0) {
                throw new InternalError();
            }
        } catch (Exception e){
            throw new InternalError(e.getMessage());
        }
    }
}
