package com.spring.smbackend.security;

import com.spring.smbackend.entities.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AppUserDetail implements UserDetails {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;
    private Date created;
    private List<GrantedAuthority> authorities;

    public AppUserDetail(AppUser user) {
        super();
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.created = user.getCreated();
        this.email = user.getEmail();
        this.password = user.getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() != null) authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

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
