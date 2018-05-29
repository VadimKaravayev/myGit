package vk.vadim.karavayev.dto;

import java.util.Objects;

public class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordRepeat;
    private String remember;
    private String verification;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public String getVerification() {
        return verification;
    }

    public String getRemember() {
        return remember;
    }

    public static UserRegistrationDtoBuilder getBuilder() {
        return new UserRegistrationDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRegistrationDto)) return false;
        UserRegistrationDto that = (UserRegistrationDto) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(passwordRepeat, that.passwordRepeat) &&
                Objects.equals(remember, that.remember) &&
                Objects.equals(verification, that.verification);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, email, password, passwordRepeat, remember, verification);
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordRepeat='" + passwordRepeat + '\'' +
                ", remember='" + remember + '\'' +
                ", verification='" + verification + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }


    public static class UserRegistrationDtoBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String passwordRepeat;
        private String remember;
        private String verification;

        public UserRegistrationDtoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserRegistrationDtoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserRegistrationDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserRegistrationDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserRegistrationDtoBuilder setPasswordRepeat(String passwordRepeat) {
            this.passwordRepeat = passwordRepeat;
            return this;
        }

        public UserRegistrationDtoBuilder setRemember(String remember) {
            this.remember = remember;
            return this;
        }

        public UserRegistrationDtoBuilder setVerification(String verification) {
            this.verification = verification;
            return this;
        }

        public UserRegistrationDto build() {
            UserRegistrationDto userDto = new UserRegistrationDto();
            userDto.firstName = this.firstName;
            userDto.lastName = this.lastName;
            userDto.email = this.email;
            userDto.password = this.password;
            userDto.passwordRepeat = this.passwordRepeat;
            userDto.remember = this.remember;
            userDto.verification = this.verification;
            return userDto;
        }
    }
}
