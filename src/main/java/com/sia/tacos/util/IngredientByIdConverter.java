package com.sia.tacos.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sia.tacos.model.Ingredient;
import com.sia.tacos.repository.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Override
	public Ingredient convert(String id) {
		return ingredientRepository.findById(id);
	}

}
