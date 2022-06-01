package com.example.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hotel.entity.Hotel;

public interface HotelService {

	Hotel getById(Long id);

	void deleteAll();

	void delete(Hotel entity);

	<S extends Hotel> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Hotel> List<S> saveAll(Iterable<S> entities);

	Optional<Hotel> findById(Long id);

	List<Hotel> findAllById(Iterable<Long> ids);
	
	List<Hotel> findAll();

	Page<Hotel> findAll(Pageable pageable);

	<S extends Hotel> S save(S entity);

	List<Hotel> findByNameContaining(String name);

	void deleteById(Long id);

	Page<Hotel> findByNameContaining(String name, Pageable pageable);

}
