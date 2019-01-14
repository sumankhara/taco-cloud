package com.sia.tacos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.model.Ingredient.Type;
import com.sia.tacos.model.Order;
import com.sia.tacos.model.Taco;
import com.sia.tacos.repository.IngredientRepository;
import com.sia.tacos.repository.TacoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private TacoRepository tacoRepository;
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
	    
	    Type[] types = Ingredient.Type.values();
	    for (Type type : types) {
	      model.addAttribute(type.toString().toLowerCase(), 
	          filterByType(ingredients, type));      
	    }
		return "design";
	}

	/*@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}		
	}*/
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, @ModelAttribute Order order) {
		if(errors.hasErrors()) {
			return "design";
		}
		
		Taco saved = tacoRepository.save(design);
		order.addDesign(saved);
		
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}
