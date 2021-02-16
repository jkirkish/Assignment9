package com.coderscampus.Assignment9.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coderscampus.Assignment9.domain.Recipe;

@Repository
public class RecipeRepository {
	
	private List<Recipe> recipes = new ArrayList<>(100);

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	
	

}
