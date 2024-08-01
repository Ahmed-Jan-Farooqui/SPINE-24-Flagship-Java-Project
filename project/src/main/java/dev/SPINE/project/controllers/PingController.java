package dev.SPINE.project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/test")
@RestController
public class PingController {
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Secured endpoint has been accessed.");
    }
}
