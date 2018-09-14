package com.zzpdream.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
//        return "redirect:/login.html";
    }

    @RequestMapping("/")
    public String index(){
        return "main";
//        return "redirect:/main.html";
    }


}
