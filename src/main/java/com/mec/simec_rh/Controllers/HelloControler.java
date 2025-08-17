package com.mec.simec_rh.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class HelloControler {

    @GetMapping
    public  String hello(){
        return "Hello";
    }

}
