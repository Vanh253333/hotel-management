package com.example.hotel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
	List<Hotel> findByNameContaining(@Param("name") String name);
	Page<Hotel> findByNameContaining(@Param("name") String name, Pageable pageable);

}
