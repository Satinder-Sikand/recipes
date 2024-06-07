package dev.satinder.recipes.user.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.satinder.recipes.user.roles.UserRole;
import dev.satinder.recipes.user.token.VerificationToken;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import dev.satinder.recipes.recipe.DTO.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Getter
@Setter
public class User {
	@Id
	@NonNull

	private String email;
	@NonNull
	private String password;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	private Integer loginAttemps;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

	private LocalDateTime lastLogin;
	private String bio;
	private String imageUrl;
	private List<Recipe> recipes;
	@NonNull
	private Set<UserRole> role;
	private boolean verified;
	@JsonIgnore
	private VerificationToken token;
	
	public User(String email, String password, String firstname, String lastname, Set<UserRole> role) {
		this.email = email;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.role = role;
		this.recipes = new ArrayList<Recipe>();
		this.bio = "";
		this.imageUrl = "";
		this.loginAttemps = 0;
		this.verified = false;
	}
	

}
