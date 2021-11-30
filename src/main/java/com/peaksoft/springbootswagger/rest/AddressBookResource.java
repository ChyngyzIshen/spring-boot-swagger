package com.peaksoft.springbootswagger.rest;

import com.peaksoft.springbootswagger.model.Contact;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(path = "/api")
public class AddressBookResource {

    ConcurrentMap<String, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    @ApiOperation(value = "Find contact by id",
            notes = "Provide an id to look up specific contact from the address book",
            response = Contact.class)
    public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve", required = true)
                              @PathVariable String id) {
        return contacts.get(id);
    }

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return new ArrayList<Contact>(contacts.values());
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact) {
        contacts.put(contact.getId(), contact);
        return contact;
    }
}
