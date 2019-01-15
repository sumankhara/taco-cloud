package com.sia.tacos.repository;

import org.springframework.data.repository.CrudRepository;

import com.sia.tacos.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
