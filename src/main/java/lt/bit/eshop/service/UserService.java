package lt.bit.eshop.service;

import lt.bit.eshop.config.CustomUserDetails;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.UserRepository;
import lt.bit.eshop.validation.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

/**
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserModel createNewUser(UserModel model) {

        UserEntity newUser = new UserEntity();

        newUser.setName(model.getName());
        newUser.setUsername(model.getUsername());
        newUser.setPassword(passwordEncoder.encode(model.getPassword()));
        newUser.setEnabled(true);

        return new UserModel(userRepository.save(newUser));
    }


    public UserModel getUserByUsername(String username) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if (!userEntity.isPresent()) {
            throw new UserNotFoundException("User not found");
        }


        return new UserModel(userEntity.get());
    }


    public static UserEntity getCurrentUser(){
        CustomUserDetails authentication = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return authentication.getUser();
    }


}
