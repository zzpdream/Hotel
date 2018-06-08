package com.zzpdream.hotel.controller;

import com.google.common.collect.Maps;
import com.zzpdream.hotel.dao.AgendaDao;
import com.zzpdream.hotel.dao.MeetDao;
import com.zzpdream.hotel.entity.Agenda;
import com.zzpdream.hotel.entity.Meet;
import com.zzpdream.hotel.entity.ReturnData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

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

    /**
     * 上传附件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        Map<String, Object> result = Maps.newHashMap();

        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
//                if (!suffixName.equalsIgnoreCase(".pdf")) {
//                    data = new ReturnData(1, "上传文件格式不正确,请上传.pdf文件", null);
//                    return data;
//                }
                String saveFilePath = "E:\\ebook\\pdf\\" + fileName;
                String parentFolder = new File(saveFilePath).getParent();
                if (!new File(parentFolder).exists()) {
                    new File(parentFolder).mkdirs();
                }
                file.transferTo(new File(saveFilePath));
                result.put("code", "0");
                result.put("msg", "上传成功");
                result.put("fileName", file.getOriginalFilename());
                result.put("url", "127.0.0.1");
            }
        } catch (Exception e) {
            result.put("code", "1");
            result.put("msg", "上传异常");
            e.printStackTrace();
        }
        return result;
    }


}
