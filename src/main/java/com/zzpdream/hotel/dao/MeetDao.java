package com.zzpdream.hotel.dao;

import com.zzpdream.hotel.entity.Meet;
import com.zzpdream.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetDao extends JpaRepository<Meet, Integer> {
    List<Meet> findBySubject(String name);
}
