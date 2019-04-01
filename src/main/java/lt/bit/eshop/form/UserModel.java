package lt.bit.eshop.form;

import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.validation.ExistUsername;

import javax.validation.constraints.NotBlank;

public class UserModel {

    @NotBlank(message = "User name is required")
    private String name;
    @NotBlank
    @ExistUsername
    private String username;
    @NotBlank
    private String password;

    public UserModel() {
    }

    public UserModel(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
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
}
