package lt.bit.eshop.form;

import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.validation.ExistUsername;
import lt.bit.eshop.validation.PasswordMatches;

import javax.validation.constraints.NotBlank;

@PasswordMatches
public class UserModel {

    @NotBlank(message = "User name is required")
    private String name;
    @NotBlank
//    @ExistUsername
    private String username = "Jonas";

    @NotBlank
    private String password;
    @NotBlank
    private String matchPassword;

    public UserModel() {
    }

    public UserModel(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
    }

    public String getName(String name) {
        return this.name;
    }

    public String getName(Integer name) {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }
}
