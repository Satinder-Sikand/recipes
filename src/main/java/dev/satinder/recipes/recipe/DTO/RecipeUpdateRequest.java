package dev.satinder.recipes.recipe.DTO;

import dev.satinder.recipes.recipe.Difficulty;
import dev.satinder.recipes.recipe.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RecipeUpdateRequest {
    private ObjectId recipeId;
    private String title;
    private String description;
    private Set<String> categories;
    private List<Ingredient> ingredients;
    private NutritionalInfo nutritionalInfo;
    private String instructions;
    private boolean checkPublic;
    private List<String> images;
    private int servings =-1;
    private int cookTime=-1;
    private int prepTime=-1;
    private Set<Difficulty> difficulty;


}
