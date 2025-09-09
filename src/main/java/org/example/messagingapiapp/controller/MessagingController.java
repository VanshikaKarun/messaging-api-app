package org.example.messagingapiapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MessagingController {

    @GetMapping("oracle")
    public String oracle() {
        return "Hi Oracle!!";
    }

    @GetMapping
    public String guest() {
        return "Hi Guest!!";
    }
}
