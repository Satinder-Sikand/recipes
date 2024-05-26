package dev.satinder.recipes.user;

import dev.satinder.recipes.user.roles.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	MongoTemplate mongoTemplate;
	
	public User createUserAdmin(String email, String password, String firstname, String lastname) {
		User user = null;
		try {
			if (!userRepository.existsById(email))
				user = userRepository.insert(new User(email, password, firstname, lastname, EnumSet.of(UserRole.ADMIN)));
		}catch (Exception e) {
			System.out.println("Could not add user.");
			e.printStackTrace();
		}
		return user;

//		mongoTemplate.update(User.class)
//		.matching(Criteria.where("imdbId").is(imdbId))
//		.apply(new Update().push("reviewIds").value(review)).first();
//		
	}

	public User createUser(String email, String password, String firstname, String lastname) {
		User user = null;
		try {
			if (!userRepository.existsById(email))
				user = userRepository.insert(new User(email, password, firstname, lastname, EnumSet.of(UserRole.USER)));
		}catch (Exception e) {
			System.out.println("Could not add user.");
			e.printStackTrace();
		}
		return user;
	}

}
