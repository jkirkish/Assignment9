package com.coderscampus.Assignment9.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.Assignment9.domain.Recipe;
import com.coderscampus.Assignment9.repository.RecipeRepository;

@Service
public class FileService {
	@Autowired
	private RecipeRepository recipeRepo;

	private List<Recipe> acceptAllRecipes() {

		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',')
											   .withEscape('\\')
											   .withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes", "Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian")
											   .withSkipHeaderRecord()
											   .withIgnoreSurroundingSpaces();

		try (Reader in = new FileReader("recipes.txt")) {
			Iterable<CSVRecord> records = csvFormat.parse(in);
			for (CSVRecord record : records) {
				Recipe recipe = new Recipe(Integer.parseInt(record.get("Cooking minutes")),
					   Boolean.parseBoolean(record.get("Dairy Free")),
					   Boolean.parseBoolean(record.get("Gluten Free")),
					   record.get("Instructions"),
				       Double.parseDouble(record.get("Preparation Minutes")),
				       Double.parseDouble(record.get("Price Per Serving")),
				       Integer.parseInt(record.get("Ready In Minutes")),
				       Integer.parseInt(record.get("Servings")),
				       Double.parseDouble(record.get("Spoonacular Score")),
				       record.get("title"),
				       Boolean.parseBoolean(record.get("vegan")),
				       Boolean.parseBoolean(record.get("Vegetarian")));
				       

				recipeRepo.getRecipes().add(recipe);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return recipeRepo.getRecipes();

	}

	public List<Recipe> getAllRecipes() {
		if (recipeRepo.getRecipes().size() == 0) {
			return acceptAllRecipes();

		}
		return recipeRepo.getRecipes();

	}
}
