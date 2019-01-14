package com.sia.tacos.repository;

import com.sia.tacos.model.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();
	
	Ingredient findById(String id);
	
	Ingredient save(Ingredient ingredient);
}
