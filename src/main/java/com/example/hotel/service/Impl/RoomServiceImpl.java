package com.example.hotel.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Room;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public <S extends Room> S save(S entity) {
		return roomRepository.save(entity);
	}

	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public List<Room> findAll(Sort sort) {
		return roomRepository.findAll(sort);
	}

	@Override
	public List<Room> findAllById(Iterable<Long> ids) {
		return roomRepository.findAllById(ids);
	}

	@Override
	public Optional<Room> findById(Long id) {
		return roomRepository.findById(id);
	}

	@Override
	public long count() {
		return roomRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		roomRepository.deleteById(id);
	}

	@Override
	public void delete(Room entity) {
		roomRepository.delete(entity);
	}

	@Override
	public Room getById(Long id) {
		return roomRepository.getById(id);
	}

	@Override
	public Page<Room> findAll(Pageable pageable) {
		return roomRepository.findAll(pageable);
	}
	
	@Override
	public Page<Room> findByNameContaining(String name, Pageable pageable) {
		return roomRepository.findByNameContaining(name, pageable);
	}
	
    @Override
	public List<Room> getFreeRoom(String checkIn, String checkOut) {
    	return roomRepository.searchRoom(checkIn, checkOut);
    }
}
