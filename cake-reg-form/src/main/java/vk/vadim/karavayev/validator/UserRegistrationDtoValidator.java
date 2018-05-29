package vk.vadim.karavayev.validator;

import vk.vadim.karavayev.constants.ErrorMessage;
import vk.vadim.karavayev.constants.SignupFormData;
import vk.vadim.karavayev.dto.UserRegistrationDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserRegistrationDtoValidator {

    private final static Pattern NAME = Pattern.compile("^([A-Z][a-z]+)((\\s|-)[A-Z][a-z]+)?$");
    private final static Pattern EMAIL =
            Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private final static Pattern PASSWORD =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");


    public Map<String, String> validate(UserRegistrationDto userDto) {
        Map<String, String> errors = new HashMap<>();
        if (!NAME.matcher(userDto.getFirstName()).find()) {
            errors.put(SignupFormData.FIRST_NAME, ErrorMessage.WRONG_NAME);
        }
        if (!NAME.matcher(userDto.getLastName()).find()) {
            errors.put(SignupFormData.LAST_NAME, ErrorMessage.WRONG_NAME);
        }
        if (!EMAIL.matcher(userDto.getEmail()).find()) {
            errors.put(SignupFormData.EMAIL, ErrorMessage.WRONG_EMAIL);
        }
        if (!PASSWORD.matcher(userDto.getPassword()).find()) {
            errors.put(SignupFormData.PASSWORD, ErrorMessage.WRONG_PASSWORD_FORMAT);
        }
        if (!Objects.equals(userDto.getPassword(), userDto.getPasswordRepeat())) {
            errors.put(SignupFormData.PASSWORD_REPEAT, ErrorMessage.WRONG_REPEAT_PASSWORD);
        }
        return errors;
    }

}
