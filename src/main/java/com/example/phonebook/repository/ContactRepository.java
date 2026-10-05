package com.example.phonebook.repository;

import com.example.phonebook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByNameContainingIgnoreCase(String name);
    List<Contact> findByPhoneContaining(String phonePart);
    List<Contact> findAllByOrderByNameAsc();
    boolean existsByPhone(String phone);
}