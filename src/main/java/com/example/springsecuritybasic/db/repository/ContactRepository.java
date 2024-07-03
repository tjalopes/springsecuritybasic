package com.example.springsecuritybasic.db.repository;


import com.example.springsecuritybasic.db.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

}
