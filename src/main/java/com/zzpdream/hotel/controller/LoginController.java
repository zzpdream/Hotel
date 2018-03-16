package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.ReturnData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/")
    public String index(){
        return "login";
//        return "redirect:/login.html";
    }

    @PostMapping("/login")
    @ResponseBody
    public ReturnData login(@RequestBody String username,@RequestBody String password){
        System.out.println("username:"+username+" password:"+password);
        return new ReturnData(0,"sucess","1");
//        return "redirect:/login.html";
    }
}
