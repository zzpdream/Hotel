package com.zzpdream.hotel.controller;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.zzpdream.hotel.dao.OrderDao;
import com.zzpdream.hotel.dao.RoomDao;
import com.zzpdream.hotel.entity.Order;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.Room;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderDao orderDao;

    @Resource
    private RoomDao roomDao;

    @ResponseBody
    @RequestMapping("/findAll")
    public ReturnData getAllOrders() {
        List orders = orderDao.findAll();
        ReturnData data = new ReturnData<List<Order>>(0, "", orders);
        data.count = orders.size();
        return data;
    }

    @ResponseBody
    @RequestMapping("/addOrder")
    public ReturnData addOrder(@RequestBody Map<String, Object> reqMap) {
        String orderName = reqMap.get("orderName").toString();
        String tel = reqMap.get("tel").toString();
        int orderRoomId = (int) reqMap.get("orderRoomId");
        String orderRoomName = reqMap.get("orderRoomName").toString();
        int totalPrice = (int) reqMap.get("totalPrice");


        String beginDate = reqMap.get("beginDate").toString() + " 12:00";
        String endDate = reqMap.get("endDate").toString() + " 12:00";

        String liveBeginDate = reqMap.get("beginDate").toString() + " 18:00";
        String liveEndDate = reqMap.get("endDate").toString() + " 12:00";

        String queryBeginDate = reqMap.get("beginDate").toString() + " 13:00";
        String queryEndDate = reqMap.get("endDate").toString() + " 11:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //分查询时间 入住时间 以及计算天数 3者要分开计算
        Order order = null;
        int days = 0;
        try {
            //计算间隔天数
            Date begin_Date = format.parse(beginDate);
            Date end_Date = format.parse(endDate);
            days = (int) ((end_Date.getTime() - begin_Date.getTime()) / (24 * 60 * 60 * 1000));

            Date query_begin_Date = format.parse(queryBeginDate);
            Date query_end_Date = format.parse(queryEndDate);

            Date live_begin_Date = format.parse(liveBeginDate);
            Date live_end_Date = format.parse(liveEndDate);

            order = new Order(orderName, totalPrice, tel, orderRoomId, orderRoomName, live_begin_Date, live_end_Date);
            if (!checkRoomCanOrder(orderRoomId, query_begin_Date, query_end_Date)) {
                return new ReturnData(0, "该房间已被预定，请重新选择", null);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!checkPriceOk(orderRoomId, totalPrice, days)) {
            return new ReturnData(0, "订单错误", null);
        }
        if (order != null) {
            orderDao.save(order);
            sendMessages(tel,orderRoomName,beginDate,endDate);
            return new ReturnData(1, "预定成功", null);
        } else {
            return new ReturnData(0, "订单错误", null);
        }

    }

    private boolean checkRoomCanOrder(int roomId, Date beginDate, Date endDate) {
        boolean result = true;
        List<Order> orders1 = orderDao.findOrdersByBeginTimeBetweenAndOrderRoomId(beginDate, endDate, roomId);
        List<Order> orders2 = orderDao.findOrdersByEndTimeBetweenAndOrderRoomId(beginDate, endDate, roomId);
        if (orders1.size() == 0 && orders2.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    private boolean checkPriceOk(int roomid, int totalPrice, int days) {
        Room currentRoom = roomDao.findRoomById(roomid);
        if (currentRoom != null) {
            if (currentRoom.getPrice() * days == totalPrice) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void sendMessages(String tel,String roomName,String beginDate,String endDate) {
        // 短信应用SDK AppID
        int appid = 1400139987; // 1400开头

        // 短信应用SDK AppKey
                String appkey = "25092a3472daec6bc99925e851a1e961";

        // 需要发送短信的手机号码
                String[] phoneNumbers = {"15962196636", "15862511691"};

        // 短信模板ID，需要在短信应用中申请
                int templateId = 191446; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        //templateId7839对应的内容是"您的验证码是: {1}"
        // 签名
        String smsSign = "西山天地"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
        try {
            String[] params = {"5678"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
    }
}
