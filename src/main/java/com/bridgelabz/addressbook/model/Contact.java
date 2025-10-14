package com.bridgelabz.addressbook.model;


import com.bridgelabz.addressbook.DTO.ContactDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String phone;

    public Contact(ContactDTO contactDTO) {
        this.name = contactDTO.getName();
        this.email = contactDTO.getEmail();
        this.phone = contactDTO.getPhone();
    }

}