package com.zzpdream.hotel.dao;

import com.zzpdream.hotel.entity.Room;
import com.zzpdream.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends JpaRepository<Room, Integer> {

    Room findRoomById(Integer id);
}
