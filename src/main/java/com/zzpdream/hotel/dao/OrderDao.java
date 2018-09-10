package com.zzpdream.hotel.dao;

import com.zzpdream.hotel.entity.Order;
import com.zzpdream.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByBeginTimeBetweenAndOrderRoomId(Date begin, Date end,int roomId);
    List<Order> findOrdersByEndTimeBetweenAndOrderRoomId(Date begin, Date end,int roomId);
}
