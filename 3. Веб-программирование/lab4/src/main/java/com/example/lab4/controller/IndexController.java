package com.example.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/login", "/register", "/main", "/"} )
    public String getIndexPage(){
        return "index.html";
    }

}
