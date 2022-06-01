package com.example.hotel.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.hotel.entity.Hotel;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
	@Autowired
	HotelRepository hotelRepository;

	public HotelServiceImpl(HotelRepository categoryRepository) {
		this.hotelRepository = categoryRepository;
	}
	
	@Override
	public List<Hotel> findByNameContaining(String name) {
		return hotelRepository.findByNameContaining(name);
	}

	@Override
	public <S extends Hotel> S save(S entity) {
		return hotelRepository.save(entity);
	}

	@Override
	public Page<Hotel> findAll(Pageable pageable) {
		return hotelRepository.findAll(pageable);
	}

	@Override
	public List<Hotel> findAllById(Iterable<Long> ids) {
		return hotelRepository.findAllById(ids);
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		return hotelRepository.findById(id);
	}

	@Override
	public <S extends Hotel> List<S> saveAll(Iterable<S> entities) {
		return hotelRepository.saveAll(entities);
	}

	@Override
	public <S extends Hotel> List<S> saveAllAndFlush(Iterable<S> entities) {
		return hotelRepository.saveAllAndFlush(entities);
	}

	@Override
	public void delete(Hotel entity) {
		hotelRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		hotelRepository.deleteAll();
	}

	@Override
	public Hotel getById(Long id) {
		return hotelRepository.getById(id);
	}

	@Override
	public Page<Hotel> findByNameContaining(String name, Pageable pageable) {
		return hotelRepository.findByNameContaining(name, pageable);
	}

	@Override
	public void deleteById(Long id) {
		hotelRepository.deleteById(id);
	}

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}
	
	
}
