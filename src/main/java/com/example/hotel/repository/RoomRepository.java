package com.example.hotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	Page<Room> findByNameContaining(@Param("name") String name, Pageable pageable);
	
    @Query(value = "SELECT * FROM rooms WHERE room_id NOT IN"
    		+ "(SELECT room_id FROM booked_rooms WHERE check_out >= :checkOut AND check_in <= :checkIn)",
            nativeQuery = true)
    List<Room> searchRoom(@Param("checkIn") String checkOut,
                            @Param("checkOut") String checkIn);
}
