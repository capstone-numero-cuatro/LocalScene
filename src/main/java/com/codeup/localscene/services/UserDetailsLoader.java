package com.codeup.localscene.services;

import com.codeup.localscene.models.UserWithRoles;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository user;

    public UserDetailsLoader(UserRepository user) {
        this.user = user;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User users = user.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new UserWithRoles(users);
    }
}

