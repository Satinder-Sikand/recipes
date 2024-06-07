package dev.satinder.recipes.recipe;

import dev.satinder.recipes.recipe.DTO.Recipe;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, ObjectId>{
    List<Recipe> findRecipeByCreatedBy(String emailId);
    @Query("{ 'createdBy': ?0. 'isPublic': ?1}")
    List<Recipe> findOtherCreatedBy(String emailId, boolean isPublic);

    List<Recipe> findRecipeByIsPublic(boolean isPublic);
}
