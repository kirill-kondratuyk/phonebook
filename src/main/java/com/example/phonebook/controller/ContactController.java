package com.example.phonebook.controller;

import com.example.phonebook.dto.CreateContactRequest;
import com.example.phonebook.dto.ContactResponse;
import com.example.phonebook.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse add(@Valid @RequestBody CreateContactRequest request) {
        return service.addContact(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteContact(id);
    }

    @GetMapping("/search/name")
    public List<ContactResponse> findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @GetMapping("/search/phone")
    public List<ContactResponse> findByPhone(@RequestParam String phone) {
        return service.findByPhonePart(phone);
    }

    @GetMapping
    public List<ContactResponse> getAll() {
        return service.getAllSorted();
    }
}