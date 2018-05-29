package vk.vadim.karavayev.converter;

import vk.vadim.karavayev.dto.UserRegistrationDto;
import vk.vadim.karavayev.entity.User;

import java.util.Objects;

public class UserSignupDtoToUser implements Converter<UserRegistrationDto, User> {
    @Override
    public User convert(UserRegistrationDto from) {
        User user = new User();
        user.setFirstName(from.getFirstName());
        user.setLastName(from.getLastName());
        user.setEmail(from.getEmail());
        user.setPassword(from.getPassword());
        user.setRemember(Objects.nonNull(from.getRemember()));
        return user;
    }
}
