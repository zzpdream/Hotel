package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.ReturnData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/")
    public String index(){
        return "login";
//        return "redirect:/login.html";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ReturnData login(){
        return new ReturnData(0,"sucess","1");
//        return "redirect:/login.html";
    }
}
