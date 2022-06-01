package com.example.hotel.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hotel.entity.BookedRoom;
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
	public BookedRoom getById(Long id) {
		return bookedRoomRepository.getById(id);
	}

	@Override
	public void delete(BookedRoom entity) {
		bookedRoomRepository.delete(entity);
	}

	@Override
	public Optional<BookedRoom> findById(Long id) {
		return bookedRoomRepository.findById(id);
	}
	
	
}
