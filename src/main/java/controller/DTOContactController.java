package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.model.Contact;
import com.bridgelabz.addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/addressbook/dto")
public class DTOContactController {
    private List<Contact> contacts = new ArrayList<>();

    @GetMapping("/contacts/get/all")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/contacts/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return ResponseEntity.ok(contact);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/contacts/add")
    public ResponseEntity<String> addContact(@RequestBody ContactDTO contactDTO) {
        Contact newContact = new Contact((long) (contacts.size() + 1), contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        contacts.add(newContact);
        return ResponseEntity.ok("Contact added successfully!");
    }

    @PutMapping("/contacts/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(contactDTO.getName());
                contact.setEmail(contactDTO.getEmail());
                contact.setPhone(contactDTO.getPhone());
                return ResponseEntity.ok("Contact updated successfully!");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/contacts/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        if (contacts.removeIf(contact -> contact.getId().equals(id))) {
            return ResponseEntity.ok("Contact deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}