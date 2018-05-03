package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.RoleDao;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleDao roleDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public ReturnData getAllUser() {
        List roles = roleDao.findAll();
        ReturnData data = new ReturnData<List<Role>>(0, "", roles);
        data.count = roles.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/add_role")
    public ReturnData addRole(String rolename) {
        ReturnData data;
        try {
            roleDao.save(new Role(rolename));
            data = new ReturnData(0, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            data = new ReturnData(1, e.getMessage(), null);
        }
        return data;
    }

    @ResponseBody
    @PostMapping("/delete_role")
    public ReturnData deleteRole(@RequestBody Role[] roles) {
        ReturnData data;
        try {
//            String[] idList = ids.replace(" ", "").split(",");
            if (roles!=null&&roles.length > 0) {
                for (Role role : roles) {
                    roleDao.delete(role);
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
