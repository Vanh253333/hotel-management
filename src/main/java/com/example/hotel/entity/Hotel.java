package com.example.hotel.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")

public class Hotel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;
	
	@Column( columnDefinition = "nvarchar(100) not null")
	private String name;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String address;
	
	@Column(nullable = false)
	private int starLevel;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String description;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Set<Room> rooms;
}
