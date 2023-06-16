package com.codeup.localscene;

import com.codeup.localscene.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/home")
                )
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/404","/img/**","/js/**", "/css/**", "/", "/sign-up",
                                "/verify", "/home","/forgot-password","/reset-password",
                                "/band-profile/{bandId}", "/venues", "/events").permitAll()
                        .requestMatchers("/profile/{id}","/profile/edit").authenticated()
//                        .anyRequest().authenticated()
                );
//
//        http /* Login configuration */
//                .formLogin((login) -> login.loginPage("/login").defaultSuccessUrl("/home"))
//                /* Logout configuration */
//                .authorizeHttpRequests((requests) -> requests
//                        /* Pages that require authentication
//                         * only authenticated users can create and edit ads */
//                        .requestMatchers("/profile/{id}","/profile/edit").authenticated()
//                        /* Pages that do not require authentication
//                         * anyone can visit the home page, register, login, and view ads */
//                        .requestMatchers("/404", "/", "/sign-up",
//                                "/verify", "/home","/forgot-password","/reset-password",
//                                "/band-profile/{bandId}", "/venues", "/events").permitAll()
//                        // allow loading of static resources
//                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
//                )
//                .logout((logout) -> logout.logoutSuccessUrl("/home"))
//                .httpBasic(withDefaults()
//                );







        return http.build();
    }

}