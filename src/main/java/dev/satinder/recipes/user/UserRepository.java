package dev.satinder.recipes.user;

import dev.satinder.recipes.user.DTO.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	

}
