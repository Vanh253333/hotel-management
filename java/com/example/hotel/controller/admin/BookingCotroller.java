package com.example.hotel.controller.admin;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hotel.domain.BookedRoom;
import com.example.hotel.domain.Room;
import com.example.hotel.model.BookedRoomDto;
import com.example.hotel.service.AppUserService;
import com.example.hotel.service.BookedRoomService;
import com.example.hotel.service.RoomService;

@Controller
@RequestMapping("site/bookings")
public class BookingCotroller {
	
	@Autowired(required = false)
	RoomService roomService;
	
	@Autowired(required = false)
	AppUserService appUserService;
	
	@Autowired(required = false)
	BookedRoomService bookedRoomService;
	
	@GetMapping("searchFreeRoom")
	public String search(ModelMap model, 
			@RequestParam(name = "checkIn", required = false) String checkIn,
			@RequestParam(name = "checkOut", required = false) String checkOut) {
		
		List<Room> list = roomService.getFreeRoom(checkIn, checkOut);	
		model.addAttribute("freeRooms", list);
		return "site/bookings/searchFreeRoom";
	}
	
	
//	@GetMapping("add")
//	public String addOrEdit(ModelMap model,
//			@RequestParam(name = "checkIn") String checkIn,
//			@RequestParam(name = "checkOut") String checkOut, 
//			@RequestParam(name = "roomId") Long roomId,
//			Principal principal) {
////		System.out.print("Kh??ng hi???n ra c??i m??? g?? c???" + checkOut + checkIn + roomId);
//		BookedRoomDto dto = new BookedRoomDto();
////		dto.setCheckIn(LocalDate.parse(checkIn));
////		dto.setCheckOut(LocalDate.parse(checkOut));
////		dto.setRoomId(roomId);
////		dto.setUserId(appUserService.getByUsername(principal.getName()).getUserId());
////		
////		int num = Period.between(LocalDate.parse(checkIn), LocalDate.parse(checkOut)).getDays();
////		double price = num*roomService.getById(roomId).getPrice();
////		
////		dto.setDayNumber(num);
////		dto.setTotalPrice(price);
//		dto.setCheckIn(LocalDate.now());
//		dto.setCheckOut(LocalDate.now().plusDays(2));
//		dto.setRoomId((long) 1);
//		dto.setUserId((long) 2);
//		dto.setDayNumber(2);
//		dto.setTotalPrice(2000000);
//		model.addAttribute("bookedRoom", dto);
//		model.addAttribute("message", "Booking is saved!");
//		
//		return "site/bookings/bookingRooms";
//	}
	
	@PostMapping("save")
	public ModelAndView save(ModelMap model,
			@RequestParam(name = "checkIn", required = false) String checkIn,
			@RequestParam(name = "checkOut", required = false) String checkOut, 
			@RequestParam(name = "roomId", required = false) Long roomId,
			Principal principal) {
//		if(result.hasErrors()) {
//			return new ModelAndView("admin/hotels/addOrEdit");
//		}
		BookedRoomDto dto = new BookedRoomDto();
		dto.setCheckIn(LocalDate.parse(checkIn));
		dto.setCheckOut(LocalDate.parse(checkOut));
		dto.setRoomId(roomId);
		dto.setUserId(appUserService.getByUsername(principal.getName()).getUserId());
		
		int num = Period.between(LocalDate.parse(checkIn), LocalDate.parse(checkOut)).getDays();
		double price = num*roomService.getById(roomId).getPrice();
		
		dto.setDayNumber(num);
		dto.setTotalPrice(price);
		
		System.out.print("Kh??ng hi???n ra c??i qq c???  " + dto.getCheckIn() + dto.getCheckOut() + dto.getRoomId());
		
		BookedRoom entity = new BookedRoom();
		
		BeanUtils.copyProperties(dto, entity);
		bookedRoomService.save(entity);
		model.addAttribute("message", "Booking is saved!");
		return new ModelAndView("redirect:/site/bookings/bookingRooms", model);
	}
	
	
	@GetMapping("delete/{bookedRoomId}")
	public ModelAndView delete(ModelMap model, @PathVariable("bookedRoomId") Long bookedRoomId) throws IOException {

		Optional<BookedRoom> otp = bookedRoomService.findById(bookedRoomId);
		if (otp.isPresent()) {
			bookedRoomService.delete(otp.get());
			model.addAttribute("message", "BookedRoom is deleted!");

		} else {
			model.addAttribute("message", "BookedRoom is not found!");
		}

		return new ModelAndView("forward:/site/bookings/searchFreeRoom", model);
	}
	
}
