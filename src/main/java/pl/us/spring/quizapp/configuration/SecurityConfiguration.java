package pl.us.spring.quizapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .realmName("QuizApp")
                .and()
                .csrf()
                .disable()
                .headers()
                .and()
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/api/v1/admin/quizzes/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/quizzes/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/quizzes/**").hasRole("USER")

                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .password(passwordEncoder().encode("1234"))
                        .username("adam")
                        .roles("USER", "ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
