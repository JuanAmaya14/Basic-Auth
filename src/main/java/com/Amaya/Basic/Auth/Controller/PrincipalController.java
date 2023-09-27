package com.Amaya.Basic.Auth.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PrincipalController {


    @GetMapping
    public String publico() {

        return "Hola mundo";

    }

    @GetMapping("/admin")
    public String admin() {

        return "Hola admin";

    }

}
