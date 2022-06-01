package com.example.hotel.service;

import java.util.List;
import java.util.Optional;

import com.example.hotel.entity.BookedRoom;

public interface BookedRoomService {

	BookedRoom getById(Long id);

	void deleteById(Long id);

	<S extends BookedRoom> S save(S entity);

	void delete(BookedRoom entity);


	Optional<BookedRoom> findById(Long id);

}
