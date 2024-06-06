package dev.satinder.recipes.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {
	private double amount;
	private String name;
	private String unit; //grams, teaspoon, etc.
	
	public Ingredient (double amount, String name, String unit) {
		checkParams(amount, name, unit);
		this.amount = amount;
		this.name = name;
		this.unit = unit;
	}

	private void checkParams(double amount, String name, String unit) {
		if (this.amount<0 || name.length()<2 || unit.length()<2) {
			throw new IllegalArgumentException("The ingredient being added does not have the correct parameter requirements.");
		}
	}

}
