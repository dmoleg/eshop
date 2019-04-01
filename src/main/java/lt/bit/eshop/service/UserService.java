package lt.bit.eshop.service;

import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
