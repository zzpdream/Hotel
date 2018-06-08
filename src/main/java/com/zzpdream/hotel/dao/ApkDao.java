package com.zzpdream.hotel.dao;

import com.zzpdream.hotel.entity.Agenda;
import com.zzpdream.hotel.entity.Apk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApkDao extends JpaRepository<Apk, Integer> {

}
