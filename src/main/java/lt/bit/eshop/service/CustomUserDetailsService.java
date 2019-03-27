package lt.bit.eshop.service;

import lt.bit.eshop.config.CustomUserDetails;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.repository.UserRepository;
import lt.bit.eshop.service.lt.bit.eshop.entity.Authority;
import lt.bit.eshop.service.lt.bit.eshop.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        Set<Role> roles = optionalUser.get().getRoles();

        Set<GrantedAuthority> authorities = roles.stream()
                .map(r -> r.getAuthorities())
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        roles.forEach(r -> {
            StringBuilder roleName = new StringBuilder("ROLE_");
            roleName.append(r.getName());
            authorities.add(new Authority(roleName.toString()));
        });


        return new CustomUserDetails(optionalUser.get(), authorities);
    }
}
