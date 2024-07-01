package com.example.springsecuritybasic.config;

import com.example.springsecuritybasic.db.model.Customer;
import com.example.springsecuritybasic.db.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EasyBankUserDetails implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Customer> customer = customerRepository.findByEmail(username);

        if(customer.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user:" + username);
        }

        return new User(username, customer.get(0).getPwd(), List.of(new SimpleGrantedAuthority(customer.get(0).getRole())));
    }
}
