package dev.satinder.recipes.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {
	private int amount;
	private String name;
	private String unit; //grams, teaspoon, etc.
	
	public Ingredient (int amount, String name, String unit) {
		this.amount = amount;
		this.name = name;
		this.unit = unit;
	}

}
