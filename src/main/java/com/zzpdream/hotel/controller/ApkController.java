package com.zzpdream.hotel.controller;

import com.google.common.collect.Maps;
import com.zzpdream.hotel.dao.AgendaDao;
import com.zzpdream.hotel.dao.ApkDao;
import com.zzpdream.hotel.entity.Agenda;
import com.zzpdream.hotel.entity.Apk;
import com.zzpdream.hotel.entity.ReturnData;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/apk")
public class ApkController {
    @Autowired
    private org.springframework.core.env.Environment env;

    @Resource
    private ApkDao apkDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public ReturnData getAllUpdateApks() {
        List users=apkDao.findAll();
        ReturnData data=new ReturnData<List<Agenda>>(0, "",users );
        data.count=users.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ReturnData addApk(Apk apk) {
        ReturnData data;
        try {
            apkDao.save(apk);
            data = new ReturnData(0, "sucess", null);
        } catch (Exception e) {
            data = new ReturnData(1, e.getMessage(), null);
            e.printStackTrace();
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/getUpdateInfo")
    public ReturnData getUpdateInfo() {
        ReturnData data;
        List<Apk> apks=apkDao.findAll();
        if(apks.size()>0){
            Apk apk=  apks.get(apks.size()-1);
            data= new ReturnData(0, "sucess", apk);
        }else{
            data= new ReturnData(1, "没有更新信息", null);
        }
        return data;
    }




    @ResponseBody
    @PostMapping("/delete")
    public ReturnData deleteApk(@RequestBody Apk[] apks) {
        ReturnData data;
        try {
//            String[] idList = ids.replace(" ", "").split(",");
            if (apks!=null&&apks.length > 0) {
                for (Apk apk : apks) {
                    apkDao.delete(apk);
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
    @RequestMapping(value = "/uploadapk", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadApk(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = Maps.newHashMap();

        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
//                if (!suffixName.equalsIgnoreCase(".pdf")) {
//                    data = new ReturnData(1, "上传文件格式不正确,请上传.pdf文件", null);
//                    return data;
//                }
//                String saveFilePath = "E:\\ebook\\pdf\\" + fileName;
                String saveFilePath = env.getProperty("resource.upload.folder") + fileName;
                String parentFolder = new File(saveFilePath).getParent();
                if (!new File(parentFolder).exists()) {
                    new File(parentFolder).mkdirs();
                }
                file.transferTo(new File(saveFilePath));
                result.put("code", "0");
                result.put("msg", "上传成功");
                result.put("name", file.getOriginalFilename());
                result.put("url", env.getProperty("resource.download.url")+saveFilePath);
            }
        } catch (Exception e) {
            result.put("code", "1");
            result.put("msg", "上传异常");
            e.printStackTrace();
        }
        return result;
    }
}
