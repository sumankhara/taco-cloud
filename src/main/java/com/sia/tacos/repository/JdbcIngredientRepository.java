package com.sia.tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sia.tacos.model.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemplate.query("select id, name, type from Ingredient",
				new RowMapper<Ingredient>() {

					@Override
					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Ingredient(
								rs.getString("id"),
								rs.getString("name"),
								Ingredient.Type.valueOf(rs.getString("type")));
					};			
		});
	}

	@Override
	public Ingredient findById(String id) {
		return jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?", 
				new RowMapper<Ingredient>() {

					@Override
					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Ingredient(
								rs.getString("id"),
								rs.getString("name"),
								Ingredient.Type.valueOf(rs.getString("type")));
					};			
		}, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());
		return ingredient;
	}

}
