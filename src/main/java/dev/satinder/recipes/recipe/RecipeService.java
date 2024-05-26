package dev.satinder.recipes.recipe;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	
	public List<Recipe> allRecipes() {
		System.out.println(Arrays.toString(recipeRepository.findAll().toArray()));
		return recipeRepository.findAll();
	}
}
