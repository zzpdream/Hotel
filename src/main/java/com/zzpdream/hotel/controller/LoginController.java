package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/login")
    public String index(){
        return "login";
//        return "redirect:/login.html";
    }

    @PostMapping("api/login")
    @ResponseBody
    public ReturnData login(User user){
        System.out.println("username:"+user.getName()+" password:"+user.getPassword());
        List<User> users=userDao.findByName(user.getName());
        if(users!=null&&users.size()>0){
            if(user.getPassword().equals(users.get(0).getPassword())){
                return new ReturnData(0,"sucess","1");
            }
            return new ReturnData(1,"密码错误","1");
        }else{
            return new ReturnData(1,"无该用户","1");
        }

//        return "redirect:/login.html";
    }
}
