package com.zzpdream.hotel.dao;

import com.zzpdream.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
