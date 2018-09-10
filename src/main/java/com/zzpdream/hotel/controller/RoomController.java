package com.zzpdream.hotel.controller;


import com.zzpdream.hotel.dao.RoomDao;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomDao roomDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public ReturnData getAllRooms() {
        List rooms=roomDao.findAll();
        ReturnData data=new ReturnData<List<Room>>(0, "",rooms );
        data.count=rooms.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/findRoomById")
    public Room getRoomById(int id ){
        Room room=roomDao.findRoomById(id);
        return room;
    }
}
