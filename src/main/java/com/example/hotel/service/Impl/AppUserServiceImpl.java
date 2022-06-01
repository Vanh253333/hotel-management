package com.example.hotel.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.entity.AppUser;
import com.example.hotel.entity.Room;
import com.example.hotel.repository.AppUserRepository;
import com.example.hotel.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService{
    @Autowired
    private AppUserRepository appUserRepository;

	@Override
	public <S extends AppUser> S save(S entity) {
		return appUserRepository.save(entity);
	}

	@Override
	public List<AppUser> findAll() {
		return appUserRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		appUserRepository.deleteById(id);
	}

	@Override
	public AppUser getById(Long id) {
		return appUserRepository.getById(id);
	}
    
	@Override
	public AppUser getByUsername(String name) {
		return appUserRepository.findByUserName(name);
	}
}
