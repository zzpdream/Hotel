package com.zzpdream.hotel.controller;

import com.zzpdream.hotel.dao.AgendaDao;
import com.zzpdream.hotel.dao.MeetDao;
import com.zzpdream.hotel.entity.Agenda;
import com.zzpdream.hotel.entity.Meet;
import com.zzpdream.hotel.entity.ReturnData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Resource
    private AgendaDao agendaDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public ReturnData getAllAgendas() {
        List users=agendaDao.findAll();
        ReturnData data=new ReturnData<List<Agenda>>(0, "",users );
        data.count=users.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/findById/{id}")
    public ReturnData getAgendasById(@PathVariable("id") Integer id) {
        List agendaList=agendaDao.findByMeetId(id);
        ReturnData data=new ReturnData<List<Agenda>>(0, "",agendaList );
        data.count=agendaList.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ReturnData addAgenda(Agenda user) {
        ReturnData data;
        try {
            agendaDao.save(user);
            data = new ReturnData(0, "", null);
        } catch (Exception e) {
            data = new ReturnData(1, e.getMessage(), null);
            e.printStackTrace();
        }
        return data;
    }




    @ResponseBody
    @PostMapping("/delete")
    public ReturnData deleteUser(@RequestBody Agenda[] users) {
        ReturnData data;
        try {
//            String[] idList = ids.replace(" ", "").split(",");
            if (users!=null&&users.length > 0) {
                for (Agenda user : users) {
                    agendaDao.delete(user);
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
