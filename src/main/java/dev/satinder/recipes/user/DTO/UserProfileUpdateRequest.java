package dev.satinder.recipes.user.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateRequest {
    private String bio;
    private String imageUrl;
    private String firstName;
    private String lastName;
    //Add more as needed
}
