package com.bridgelabz.addressbook.controller;


import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.Service.ContactService;
import com.bridgelabz.addressbook.model.Contact;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/addressbook/service")
public class ServiceContactController {

    private final ContactService contactService;

    // Constructor Injection
    public ServiceContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET - Fetch All Contacts
    @GetMapping("/contacts/get/all")
    public ResponseEntity<List<Contact>> getAllContacts() {
        log.info("Received request to fetch all contacts");
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    // GET - Fetch Contact by ID
    @GetMapping("/contacts/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        log.info("Received request to fetch contact with ID: {}", id);
        Contact contact = contactService.getContactById(id);
        return (contact != null) ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    // POST - Add New Contact
    @PostMapping("/contacts/add")
    public ResponseEntity<String> addContact( @Valid @RequestBody ContactDTO contactDTO) {
        log.info("Received request to add a new contact: {}", contactDTO);
        return ResponseEntity.ok(contactService.addContact(contactDTO));
    }

    // PUT - Update Contact
    @PutMapping("/contacts/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO contactDTO) {
        log.info("Received request to update contact with ID: {}", id);
        return ResponseEntity.ok(contactService.updateContact(id, contactDTO));
    }

    // DELETE - Remove Contact
    @DeleteMapping("/contacts/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        log.info("Received request to delete contact with ID: {}", id);
        return ResponseEntity.ok(contactService.deleteContact(id));
    }
}
