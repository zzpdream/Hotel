package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.MeetDao;
import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.Meet;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/meet")
public class MeetController {

    @Resource
    private MeetDao meetDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public ReturnData getAllUser() {
        List users=meetDao.findAll();
        ReturnData data=new ReturnData<List<Meet>>(0, "",users );
        data.count=users.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ReturnData addUser(Meet user) {
        ReturnData data;
        try {
            meetDao.save(user);
            data = new ReturnData(0, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            data = new ReturnData(1, e.getMessage(), null);
        }
        return data;
    }




    @ResponseBody
    @PostMapping("/delete")
    public ReturnData deleteUser(@RequestBody Meet[] users) {
        ReturnData data;
        try {
//            String[] idList = ids.replace(" ", "").split(",");
            if (users!=null&&users.length > 0) {
                for (Meet user : users) {
                    meetDao.delete(user);
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
