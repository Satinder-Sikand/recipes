package dev.satinder.recipes.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecipeRequest {
    private String title;
    private String description;
    private List<Ingredient> ingredients;
    @JsonProperty
    private boolean isPublic;
    private String createdBy;
    private LocalDateTime createdAt;
    private int servings;
}
