package com.sia.tacos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sia.tacos.model.Order;
import com.sia.tacos.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/current")
	public String orderForm(Model model) {
//		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		orderRepository.save(order);
		sessionStatus.setComplete();
		
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
}
