package com.samanvay.springboot.tutorial.controller;

/* Meaning:
 1. Controller identifies as Controller Stereotype as well a Component.
 2. This Component will always have and return Response Body.
*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/")
    public String helloWorld () {
        return "Welcome to Samanvay Web App";
    }



}
