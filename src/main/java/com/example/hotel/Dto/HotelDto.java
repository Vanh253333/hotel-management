package com.example.hotel.Dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto implements Serializable{
	
	private Long hotelId;
	@NotEmpty
	@Length(min = 5)
	private String name;
	@NotEmpty
	@Length(min = 5)
	private String address;
	@NotNull
	private int starLevel;

	private String description;
	private Boolean isEdit = false; 
}
