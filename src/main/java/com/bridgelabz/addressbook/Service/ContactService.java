package com.bridgelabz.addressbook.Service;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.Exception.AddressBookException;
import com.bridgelabz.addressbook.model.Contact;
import com.bridgelabz.addressbook.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Get All Contacts
    public List<Contact> getAllContacts() {
        log.info("Fetching all contacts from database...");
        return contactRepository.findAll();
    }

    // Get Contact by ID
    public Contact getContactById(Long id) {
        log.info("Fetching contact with ID from database: {}", id);
        return contactRepository.findById(id)
                .orElseThrow(() -> new AddressBookException("Address Book ID " + id + " not found"));
    }

    // Add New Contact
    public String addContact(ContactDTO contactDTO) {
        Contact newContact = new Contact(null, contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        contactRepository.save(newContact);
        log.info("Added new contact to database: {}", newContact);
        return "Contact added successfully to database!";
    }

    // Update Contact
    public String updateContact(Long id, ContactDTO contactDTO) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            log.info("Updating contact with ID from database: {}", id);
            contact.setName(contactDTO.getName());
            contact.setEmail(contactDTO.getEmail());
            contact.setPhone(contactDTO.getPhone());
            contactRepository.save(contact);
            return "Contact updated successfully in database!";
        } else {
            log.error("Contact with ID {} not found in database!", id);
            return "Contact not found in database!";
        }
    }

    // Delete Contact
    public String deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            log.info("Deleted contact with ID from database: {}", id);
            return "Contact deleted successfully from database!";
        } else {
            log.error("Failed to delete. Contact with ID {} not found in database!", id);
            return "Contact not found in database!";
        }
    }
}