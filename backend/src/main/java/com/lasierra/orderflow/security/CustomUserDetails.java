package com.lasierra.orderflow.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lasierra.orderflow.usuario.entity.Usuario;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Short id;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.enabled = !usuario.getIsDeleted();
        this.authorities = builAdAuthorities(usuario);
    }

    private static List<GrantedAuthority> builAdAuthorities(Usuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));
        usuario.getRol().getPrivilegios().forEach(privilegio -> 
            authorities.add(new SimpleGrantedAuthority(privilegio.getNombre()))
        );
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
