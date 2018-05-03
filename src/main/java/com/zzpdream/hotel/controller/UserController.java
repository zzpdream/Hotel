package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.Role;
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
    public ReturnData getAllUser() {
        List users=userDao.findAll();
        ReturnData data=new ReturnData<List<User>>(0, "",users );
        data.count=users.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/add_user")
    public ReturnData addRole(User user) {
        ReturnData data;
        try {
            userDao.save(user);
            data = new ReturnData(0, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            data = new ReturnData(1, e.getMessage(), null);
        }
        return data;
    }


}
