package com.example.phonebook.mapping;

import com.example.phonebook.dto.CreateContactRequest;
import com.example.phonebook.dto.ContactResponse;
import com.example.phonebook.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactMapper {

    public Contact toEntity(CreateContactRequest request) {
        return Contact.builder()
                .name(request.name())
                .phone(request.phone())
                .email(request.email())
                .build();
    }

    public ContactResponse toResponse(Contact contact) {
        return new ContactResponse(
                contact.getId(),
                contact.getName(),
                contact.getPhone(),
                contact.getEmail()
        );
    }

    public List<ContactResponse> toResponseList(List<Contact> contacts) {
        return contacts.stream().map(this::toResponse).toList();
    }
}