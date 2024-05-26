package dev.satinder.recipes.recipe;

import java.time.LocalDateTime;
import java.util.List;

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
}

class NutritionalInfo {
	private int calories;
	//Add other nutritional fields later
}