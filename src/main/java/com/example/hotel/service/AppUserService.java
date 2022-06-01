package com.example.hotel.service;

import java.util.List;

import com.example.hotel.entity.AppUser;

public interface AppUserService {

	AppUser getById(Long id);

	void deleteById(Long id);

	List<AppUser> findAll();

	<S extends AppUser> S save(S entity);

	AppUser getByUsername(String name);

}
