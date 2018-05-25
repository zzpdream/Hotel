package com.zzpdream.hotel.dao;

import com.zzpdream.hotel.entity.Agenda;
import com.zzpdream.hotel.entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaDao extends JpaRepository<Agenda, Integer> {
    List<Agenda> findByMeetId(int id);
}
