package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.Role;
import com.zzpdream.hotel.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        List users = userDao.findAll();
        ReturnData data;
        if (users != null && users.size() > 0) {
            data = new ReturnData<List<User>>(0, "", users);
            data.count = users.size();
        } else {
            data = new ReturnData(0, "数据为空", "");
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/add_user")
    public ReturnData addUser(User user) {
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


    @ResponseBody
    @PostMapping("/delete_user")
    public ReturnData deleteUser(@RequestBody User[] users) {
        ReturnData data;
        try {
//            String[] idList = ids.replace(" ", "").split(",");
            if (users != null && users.length > 0) {
                for (User user : users) {
                    userDao.delete(user);
                }
                data = new ReturnData(0, "", null);
            } else {
                data = new ReturnData(1, "传入数据错误", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            data = new ReturnData(1, e.getMessage(), null);
        }
        return data;
    }


}
