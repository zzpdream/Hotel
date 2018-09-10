package com.zzpdream.hotel.controller;


import com.zzpdream.hotel.dao.OrderDao;
import com.zzpdream.hotel.dao.RoomDao;
import com.zzpdream.hotel.entity.Order;
import com.zzpdream.hotel.entity.ReturnData;
import com.zzpdream.hotel.entity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
        String beginDate = reqMap.get("beginDate").toString();
        String endDate = reqMap.get("endDate").toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Order order = null;
        int days = 0;
        try {
            Date begin_Date = format.parse(beginDate);
            Date end_Date = format.parse(endDate);
            days = (int) ((end_Date.getTime() - begin_Date.getTime()) / (24 * 60 * 60 * 1000));
            order = new Order(orderName, totalPrice, tel, orderRoomId, orderRoomName, begin_Date, end_Date);
            if (!checkRoomCanOrder(orderRoomId,begin_Date,end_Date)) {
                return new ReturnData(0, "该房间已被预定，请重新选择", null);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!checkPriceOk(orderRoomId, totalPrice, days)){
            return new ReturnData(0, "订单错误", null);
        }
        if(order!=null){
            orderDao.save(order);
            return new ReturnData(1, "预定成功", null);
        }else{
            return new ReturnData(0, "订单错误", null);
        }

    }

    private boolean checkRoomCanOrder(int roomId, Date beginDate, Date endDate) {
        boolean result = true;
        List<Order> orders1 = orderDao.findOrdersByBeginTimeBetweenAndOrderRoomId(beginDate, endDate,roomId);
        List<Order> orders2 = orderDao.findOrdersByEndTimeBetweenAndOrderRoomId(beginDate, endDate,roomId);
        if(orders1.size()==0&&orders2.size()==0){
            return true;
        }else{
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
}
