package com.Asset.BlackDoorzHotel.AnotherConfigturation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorityList = new ArrayList<>();

    public AppUserDetails(UserLoged userLoged){
        this.username=userLoged.getUsername();
        this.password=userLoged.getPassword();
        this.authorityList.add(new SimpleGrantedAuthority(userLoged.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
//
//    public String getRole() {
//        return this.role;
//    }

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
