package dev.SPINE.project.controllers;

import dev.SPINE.project.requests.ContactDeleteRequest;
import dev.SPINE.project.requests.ContactInitRequest;
import dev.SPINE.project.contact.ContactService;
import dev.SPINE.project.requests.ContactUpdateRequest;
import dev.SPINE.project.responses.ContactInitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;
    @PostMapping("/add")
    public ResponseEntity<ContactInitResponse> addContact(@RequestBody ContactInitRequest request){
        System.out.println("Received request on Add Contact");
        var response = contactService.save(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteContact(@RequestBody ContactDeleteRequest request){
        System.out.println("Received request on Delete Contact");
        var response = contactService.delete(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateContact(@RequestBody ContactUpdateRequest request){
        System.out.println("Received request on Delete Contact");
        var response = contactService.update(request);
        return ResponseEntity.ok(response);
    }


}
