package com.human.hanmat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @GetMapping("/")
    public String index() {
        return "index-/";
    }

    @GetMapping("/api")
    public String api() {
        return "api-/";
    }
}
