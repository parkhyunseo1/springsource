package com.example.memo.controller;

import org.springframework.stereotype.Controller;

import groovy.util.logging.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String homeString() {
        return "index";
    }

}
