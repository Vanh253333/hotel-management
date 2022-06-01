package com.example.hotel.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookedRooms")
public class BookedRoom implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookedRoomId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;
	
	@Column(nullable = false)
	private int dayNumber;

	
	@ManyToOne
	@JoinColumn(name = "roomId", nullable=false)
	private Room room;
	
	@Column(nullable = false)
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "User_Id", nullable=false)
	private AppUser appuser;
	
	
}
