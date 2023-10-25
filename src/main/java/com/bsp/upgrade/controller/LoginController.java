package com.bsp.upgrade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/{username}/{password}")
    public String doLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
        if (username.equals("admin") && password.equals("admin"))
            return "sucess";
        else
            return "fail";

    }
}

