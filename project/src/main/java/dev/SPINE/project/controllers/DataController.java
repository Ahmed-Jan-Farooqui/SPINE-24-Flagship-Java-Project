package dev.SPINE.project.controllers;


import dev.SPINE.project.contact.Contact;
import dev.SPINE.project.contact.ContactService;
import dev.SPINE.project.user.User;
import dev.SPINE.project.user.UserDTO;
import dev.SPINE.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {
    private final UserService userService;
    private final ContactService contactService;
    @GetMapping("/user")
    public UserDTO getUser(@RequestParam Integer id,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUserById(id, pageable);
    }

    @GetMapping("/contact")
    public Page<Contact> getContacts(
            @RequestParam Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return contactService.getContacts(id, pageable);
    }
}
