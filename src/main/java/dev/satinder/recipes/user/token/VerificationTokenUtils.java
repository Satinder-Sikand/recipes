package dev.satinder.recipes.user.token;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerificationTokenUtils {
    private static final int EXPIRATION_HOURS = 1;

    public static VerificationToken createToken() {
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDate = LocalDateTime.now().plusHours(EXPIRATION_HOURS);
        return new VerificationToken(token, expirationDate);
    }
}
