package dev.satinder.recipes.recipe;

import dev.satinder.recipes.recipe.DTO.Recipe;
import dev.satinder.recipes.recipe.DTO.RecipeUpdateRequest;
import dev.satinder.recipes.user.DTO.User;
import dev.satinder.recipes.user.DTO.UserProfileUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	@Autowired
	private RecipeService recipeService;

	@GetMapping
	public ResponseEntity<List<Recipe>> getAllRecipes() {
		return new ResponseEntity<List<Recipe>>(recipeService.getAllRecipes(), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<Recipe>> getRecipesByUser(@PathVariable String emailId, @RequestBody Map<String, String> payload) {
		return new ResponseEntity<List<Recipe>>(recipeService.getAllRecipesByUser(emailId, payload.get("emailId")), HttpStatus.OK);
	}

	@PostMapping("/create/recipe")
	public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeRequest recipeRequest) {
		Recipe recipe = null;
		System.out.println(recipeRequest.isPublic());
		recipe = recipeService.createRecipe(recipeRequest.getTitle(), recipeRequest.getDescription(), recipeRequest.getIngredients(), recipeRequest.isPublic(), recipeRequest.getCreatedBy(), recipeRequest.getCreatedAt(), recipeRequest.getServings());
		return new ResponseEntity<Recipe>(recipe, recipe==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

	@GetMapping("/admin/get")
	public ResponseEntity<List<Recipe>> createRecipe(@RequestBody Map<String, String> payload) {
		List<Recipe> recipes = recipeService.getAllRecipesAdmin(payload.get("emailId"));
		return new ResponseEntity<List<Recipe>>(recipes, recipes==null ? HttpStatus.UNAUTHORIZED : HttpStatus.CREATED);
	}

	@PostMapping("/update/{email}")
	public ResponseEntity<Recipe> createUser(@PathVariable String email, @RequestBody RecipeUpdateRequest recipeUpdateRequest) {
		System.out.println("Update Recipe");
		Recipe recipe = recipeService.updateRecipe(email, recipeUpdateRequest);
		return new ResponseEntity<Recipe>(recipe, recipe==null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

}
