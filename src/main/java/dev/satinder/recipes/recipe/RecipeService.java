package dev.satinder.recipes.recipe;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import dev.satinder.recipes.user.DTO.User;
import dev.satinder.recipes.user.UserRepository;
import dev.satinder.recipes.user.roles.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Recipe> getAllRecipesAdmin(String emailId) {
		if (userRepository.existsById(emailId) && userRepository.findById(emailId).get().getRole().contains(UserRole.ADMIN)) {
			System.out.println(Arrays.toString(recipeRepository.findAll().toArray()));
			return recipeRepository.findAll();
		} else {
			return null;
		}
	}

	public List<Recipe> getAllRecipes() {
		System.out.println(Arrays.toString(recipeRepository.findRecipeByIsPublic(true).toArray()));
		return recipeRepository.findRecipeByIsPublic(true);
	}

	public List<Recipe> getAllRecipesByUser(String emailId, String userId) {
		if (emailId == userId) {
			System.out.println(Arrays.toString(recipeRepository.findAll().toArray()));
			return userRepository.findById(emailId).get().getRecipes();
		} else {
			System.out.println(Arrays.toString(recipeRepository.findOtherCreatedBy(emailId, false).toArray()));
			return recipeRepository.findOtherCreatedBy(emailId, false);
		}

	}

	public Recipe createRecipe(String title, String description, List<Ingredient> ingredients, boolean isPublic, String createdBy, LocalDateTime createdAt, int servings) {
		Recipe recipe = null;
		try {
			if(!(title.length()<5 || description.length()<10 || !checkUserExists(createdBy) || servings<=0)) {
				recipe = recipeRepository.insert(new Recipe(title, description, ingredients, isPublic, createdBy, createdAt, servings));
				mongoTemplate.update(User.class)
						.matching(Criteria.where("_id").is(createdBy))
						.apply(new Update().push("recipes").value(recipe)).first();
			}
		}catch (Exception e) {
			System.out.println("Could not add Recipe. Check thrown error.");
			e.printStackTrace();
		}
		return recipe;
	}

	private boolean checkUserExists(String user) {
		return userRepository.existsById(user);
	}
}
