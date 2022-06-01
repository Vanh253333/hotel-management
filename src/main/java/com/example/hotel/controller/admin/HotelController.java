package com.example.hotel.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.example.hotel.Dto.HotelDto;
import com.example.hotel.entity.Hotel;
import com.example.hotel.service.HotelService;

@Controller
@RequestMapping("admin/hotels")
public class HotelController {
	@Autowired(required = false)
	HotelService hotelService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("hotel", new HotelDto());
		model.addAttribute("message", "Hotel is saved!");
		return "admin/hotels/addOrEdit";
	}
	
	@GetMapping("edit/{hotelId}")
	public ModelAndView edit(ModelMap model, @PathVariable("hotelId") Long hotelId) {
		Optional<Hotel> opt = hotelService.findById(hotelId);
		HotelDto dto = new HotelDto();
		if(opt.isPresent()) {
			Hotel entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			
			model.addAttribute("hotel", dto);
			model.addAttribute("message", "Hotel is saved!");
			return new ModelAndView("admin/hotels/addOrEdit", model);
		}
		model.addAttribute("message", "Hotel is not existed!");
		return new ModelAndView("forward:/admin/hotels/searchpaginated", model);
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("hotel") HotelDto dto, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/hotels/addOrEdit");
		}
		
		Hotel entity = new Hotel();
		BeanUtils.copyProperties(dto, entity);
		hotelService.save(entity);
		model.addAttribute("message", "Hotel is saved!");
		return new ModelAndView("redirect:/admin/hotels/searchpaginated", model);
	}
	
	@GetMapping("searchpaginated")
	public String search(ModelMap model, 
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
		Page<Hotel> resultPage = null;

		
		if(StringUtils.hasText(name)) {
			resultPage = hotelService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		}else {
			resultPage = hotelService.findAll(pageable);
		}
		
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.max(currentPage + 2, totalPages);
			
			if(totalPages > 5) {
				if(end == totalPages) {
					start = end - 5;
				}else if(start == 1){
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.range(start, end)
					.boxed().collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("hotelPage", resultPage);
		return "admin/hotels/searchpaginated";
	}
}
