package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.domain.BookedRoom;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long>{

}
