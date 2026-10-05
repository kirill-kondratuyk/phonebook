package com.example.phonebook.service;

import com.example.phonebook.dto.CreateContactRequest;
import com.example.phonebook.dto.ContactResponse;
import com.example.phonebook.entity.Contact;
import com.example.phonebook.mapping.ContactMapper;
import com.example.phonebook.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    public ContactResponse addContact(CreateContactRequest request) {
        if (repository.existsByPhone(request.phone())) {
            throw new RuntimeException("Contact with this phone already exists");
        }
        Contact contact = mapper.toEntity(request);
        return mapper.toResponse(repository.save(contact));
    }

    public void deleteContact(Long id) {
        Contact contact = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        repository.delete(contact);
    }

    public List<ContactResponse> findByName(String name) {
        return mapper.toResponseList(repository.findByNameContainingIgnoreCase(name));
    }

    public List<ContactResponse> findByPhonePart(String phonePart) {
        return mapper.toResponseList(repository.findByPhoneContaining(phonePart));
    }

    public List<ContactResponse> getAllSorted() {
        return mapper.toResponseList(repository.findAllByOrderByNameAsc());
    }
}