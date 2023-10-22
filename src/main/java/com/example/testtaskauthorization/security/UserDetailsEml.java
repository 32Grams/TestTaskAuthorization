package com.example.testtaskauthorization.security;

import com.example.testtaskauthorization.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
// Класс для того что бы собрать инфу для UserDetailsService

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsEml implements UserDetails {
    private long id;
    private String name;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsEml(long id, String name, String email, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsEml build(UserEntity userEntity) {
        List<GrantedAuthority> authorityList =
                List.of(new SimpleGrantedAuthority(userEntity.getRole().name()));
        return new UserDetailsEml(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
