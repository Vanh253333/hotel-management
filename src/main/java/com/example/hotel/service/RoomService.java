package com.example.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Room;

public interface RoomService {

	Room getById(Long id);

	void delete(Room entity);

	void deleteById(Long id);

	long count();

	Optional<Room> findById(Long id);

	List<Room> findAllById(Iterable<Long> ids);

	List<Room> findAll(Sort sort);

	List<Room> findAll();

	<S extends Room> S save(S entity);

	Page<Room> findByNameContaining(String name, Pageable pageable);

	Page<Room> findAll(Pageable pageable);

	List<Room> getFreeRoom(String checkIn, String checkOut);

}
