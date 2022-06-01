package com.example.hotel.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.hotel.Dto.HotelDto;
import com.example.hotel.Dto.RoomDto;
import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Room;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.StorageService;

@Controller
@RequestMapping("admin/rooms")
public class RoomController {
	@Autowired(required = false)
	HotelService hotelService;

	@Autowired(required = false)
	StorageService storageService;

	@Autowired(required = false)
	RoomService roomService;

	@ModelAttribute("hotels")
	public List<HotelDto> getCategories() {
		return hotelService.findAll().stream().map(item -> {
			HotelDto dto = new HotelDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@GetMapping("add")
	public String add(Model model) {
		RoomDto dto = new RoomDto();
		dto.setIsEdit(false);
		model.addAttribute("room", dto);
		model.addAttribute("message", "Room is saved!");
		return "admin/rooms/addOrEdit";
	}

	@GetMapping("edit/{roomId}")
	public ModelAndView edit(ModelMap model, @PathVariable("roomId") Long roomId) {
		Optional<Room> opt = roomService.findById(roomId);
		RoomDto dto = new RoomDto();
		if (opt.isPresent()) {
			Room entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setHotelId(entity.getHotel().getHotelId());
			dto.setIsEdit(true);

			model.addAttribute("room", dto);
			model.addAttribute("message", "Room is saved!");
			return new ModelAndView("admin/rooms/addOrEdit", model);
		}
		model.addAttribute("message", "Room is not existed");
		return new ModelAndView("forward:/admin/rooms/searchpaginated", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(@Valid @ModelAttribute("room") RoomDto dto,
			BindingResult result, ModelMap model) {
//		if (result.hasErrors()) {
//			return new ModelAndView("admin/rooms/addOrEdit");
//		}

		Room entity = new Room();
		BeanUtils.copyProperties(dto, entity);

		Hotel hotel = new Hotel();
		hotel.setHotelId(dto.getHotelId());
		entity.setHotel(hotel);

		if (!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();

			entity.setImage(storageService.getStorageFilename(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), entity.getImage());
		}

		roomService.save(entity);
		model.addAttribute("message", "Room is saved!");
		return new ModelAndView("redirect:/admin/rooms/searchpaginated", model);
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("delete/{roomId}")
	public ModelAndView delete(ModelMap model, @PathVariable("roomId") Long roomId) throws IOException {

		Optional<Room> otp = roomService.findById(roomId);
		if (otp.isPresent()) {
			if (!StringUtils.isEmpty(otp.get().getImage())) {
				storageService.delete(otp.get().getImage());
			}
			roomService.delete(otp.get());
			model.addAttribute("message", "Room is deleted!");

		} else {
			model.addAttribute("message", "Room is not found!");
		}

		return new ModelAndView("forward:/admin/rooms/searchpaginated", model);
	}

	@GetMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Room> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = roomService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = roomService.findAll(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.max(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.range(start, end).boxed().collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("roomPage", resultPage);
		return "admin/rooms/searchpaginated";
	}
}
