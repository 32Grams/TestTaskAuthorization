package com.example.testtaskauthorization.security;

import com.example.testtaskauthorization.entity.UserEntity;
import com.example.testtaskauthorization.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//// Класс для того что бы собрать инфу для SecurityConfiguration
@Service
@AllArgsConstructor
public class UserDetailsServiceIml implements UserDetailsService {
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with email '"+username+"' not found"));

        return UserDetailsEml.build(userEntity);
    }
}
