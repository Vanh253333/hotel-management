package com.example.hotel.Dto;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto implements Serializable{
	private Long roomId;
	
	@NotEmpty
	@Length(min = 5)
	private String name;
	
	@NotEmpty
	@Length(min = 5)
	private String type;
	
	@NotNull
	private double price;
	
	private String description;
	
	private String image;
	private MultipartFile imageFile;
	
	private Boolean isEdit = false;
	
	@NotNull
	private Long hotelId;

}
