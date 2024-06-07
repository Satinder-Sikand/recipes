package dev.satinder.recipes.user;

import dev.satinder.recipes.user.DTO.User;
import dev.satinder.recipes.user.DTO.UserProfileUpdateRequest;
import dev.satinder.recipes.user.emails.EmailServiceImpl;
import dev.satinder.recipes.user.roles.UserRole;
import dev.satinder.recipes.user.token.VerificationToken;
import dev.satinder.recipes.user.token.VerificationTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
	@Autowired
	EmailServiceImpl emailService;
	
	public User createUserAdmin(String email, String password, String firstname, String lastname) {
		User user = null;
		try {
			if (!userRepository.existsById(email) || password.length()<6) {
				user = userRepository.insert(new User(email, password, firstname, lastname, EnumSet.of(UserRole.ADMIN)));
				registerUser(user);
			}
		}catch (Exception e) {
			System.out.println("Could not add user. Check thrown error.");
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
			if (!userRepository.existsById(email)) {
				user = userRepository.insert(new User(email, password, firstname, lastname, EnumSet.of(UserRole.USER)));
//				user = new User(email, password, firstname, lastname, EnumSet.of(UserRole.USER));
				registerUser(user);
			}
		}catch (Exception e) {
			System.out.println("Could not add user.");
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	private void registerUser(User user) {
		// Generate a verification token
		user.setToken(VerificationTokenUtils.createToken());
		userRepository.save(user);

		// Send verification email
		emailService.sendNewAccountEmail(user.getFirstName(), user.getEmail(), user.getToken().getToken());
	}

	public HttpStatus verifyUser(String token, String email) {
		if (userRepository.existsById(email)) {
			User user = userRepository.findById(email).get();
			VerificationToken userToken = user.getToken();
			if (user.isVerified() || !userToken.getToken().equals(token)) {
				return HttpStatus.UNAUTHORIZED;
			}
			if (userToken.isExpired()) {
				user.setToken(VerificationTokenUtils.createToken());
				userRepository.save(user);

				// Send verification email
				emailService.sendNewAccountEmail(user.getFirstName(), user.getEmail(), user.getToken().getToken());
				return HttpStatus.UNAUTHORIZED;
			}
			user.setVerified(true);
			user.setToken(null);
			userRepository.save(user);
			return HttpStatus.ACCEPTED;

		} else {
			return HttpStatus.BAD_REQUEST ;
		}
	}

	public User updateUserProfile(String email, UserProfileUpdateRequest updateRequest) {
		User user = userRepository.findById(email).orElse(null);
		if (user != null) {
			// Apply updates if the fields are not null
			if (updateRequest.getBio() != null) {
				user.setBio(updateRequest.getBio());
			}
			if (updateRequest.getImageUrl() != null) {
				user.setImageUrl(updateRequest.getImageUrl());
			}
			if (updateRequest.getFirstName() != null) {
				user.setFirstName(updateRequest.getFirstName());
			}
			if (updateRequest.getLastName() != null) {
				user.setLastName(updateRequest.getLastName());
			}
			userRepository.save(user);
		}
		return user;
	}


}
