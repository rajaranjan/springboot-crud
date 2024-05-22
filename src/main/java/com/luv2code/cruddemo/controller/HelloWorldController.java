package com.luv2code.cruddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    // controller method to show the html form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // controller method to process the html form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

}
