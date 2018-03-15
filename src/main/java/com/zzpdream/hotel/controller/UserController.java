package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public List<User> getAllUser(){
        return  userDao.findAll();
    }


}
