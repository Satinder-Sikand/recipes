package dev.satinder.recipes.recipe.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import dev.satinder.recipes.recipe.Difficulty;
import dev.satinder.recipes.recipe.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "recipes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
	@Id
	@NonNull
	private ObjectId recipeId;
	@NonNull
	private String title;
	@NonNull
	private String description;
	private Set<String> categories;
	@NonNull
	private List<Ingredient> ingredients;
	private String instructions;
	private NutritionalInfo nutritionalInfo;
	@NonNull
	private boolean isPublic;
	@NonNull
	private String createdBy;
	@NonNull
	private LocalDateTime createdAt;
	private List<String> images;
	@NonNull
	private int servings;
	private int cookTime;
	private int prepTime;
	private Set<Difficulty> difficulty;

	public Recipe(String title, String description, List<Ingredient> ingredients, boolean isPublic, String createdBy, LocalDateTime createdAt, int servings){
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
		this.isPublic = isPublic;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.servings = servings;
	}

}

class NutritionalInfo {
	private int calories;
	//Add other nutritional fields later
}