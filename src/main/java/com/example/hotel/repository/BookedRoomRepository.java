package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entity.BookedRoom;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long>{

}
