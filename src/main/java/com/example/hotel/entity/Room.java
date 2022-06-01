package com.example.hotel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	private String name;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String type;
	
	@Column(nullable = false)
	private double price;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String description;
	
	@Column(length = 200)
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "hotelId", nullable=false)
	private Hotel hotel;
}
