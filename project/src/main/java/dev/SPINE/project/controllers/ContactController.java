package dev.SPINE.project.controllers;

import dev.SPINE.project.contact.Contact;
import dev.SPINE.project.requests.ContactDeleteRequest;
import dev.SPINE.project.requests.ContactInitRequest;
import dev.SPINE.project.contact.ContactService;
import dev.SPINE.project.requests.ContactUpdateRequest;
import dev.SPINE.project.responses.ContactInitResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;
    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    @PostMapping("/add")
    public ResponseEntity<ContactInitResponse> addContact(@RequestBody ContactInitRequest request){
        logger.info("Received request on Add Contact");
        var response = contactService.save(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteContact(@RequestBody ContactDeleteRequest request){
        logger.info("Received request on Delete Contact");
        var response = contactService.delete(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateContact(@RequestBody ContactUpdateRequest request){
        logger.info("Received request on Update Contact");
        var response = contactService.update(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public Page<Contact> searchContact(@RequestParam Integer userId, @RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer size){
        logger.info("Recevied request on Search Contact");
        Pageable pageable = PageRequest.of(page, size);
        return contactService.search(userId, keyword, pageable);
    }

}
