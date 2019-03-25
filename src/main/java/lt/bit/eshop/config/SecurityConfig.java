package lt.bit.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/products").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("secret")
                    .roles("USER")
                    .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN", "USER")
                    .build();

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager();

        ((InMemoryUserDetailsManager) userDetailsService).createUser(user);
        ((InMemoryUserDetailsManager) userDetailsService).createUser(admin);

        return userDetailsService;
    }
}
