package com.example.hotel.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hotel.domain.BookedRoom;
import com.example.hotel.repository.BookedRoomRepository;
import com.example.hotel.service.BookedRoomService;

public class BookedRoomServiceImpl implements BookedRoomService{
	@Autowired
	BookedRoomRepository bookedRoomRepository;

	@Override
	public <S extends BookedRoom> S save(S entity) {
		return bookedRoomRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		bookedRoomRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		bookedRoomRepository.deleteAll();
	}

	@Override
	public BookedRoom getById(Long id) {
		return bookedRoomRepository.getById(id);
	}
	
	
}
