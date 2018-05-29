package vk.vadim.karavayev.constants;

public class ErrorMessage {
    public static final String WRONG_NAME = "*Name must start with uppercase and contain only letter characters.";
    public static final String WRONG_EMAIL = "*Email must comply with 'name@mail.com' pattern";
    public static final String WRONG_PASSWORD_FORMAT = "*Password must contain at least 1 Uppercase, 1 number, " +
            "1 special symbol and have length >= 8";
    public static final String WRONG_REPEAT_PASSWORD = "*Passwords do not match";
    public static final String WRONG_VERIFICATION = "*Wrong verification code";
    public static final String USER_ALREADY_REGISTERED = "*There is already a user with such email";

    private ErrorMessage() {
    }
}
