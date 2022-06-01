package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	AppUser findByUserName(String userName);
}
