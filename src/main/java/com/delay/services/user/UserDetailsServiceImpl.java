package com.delay.services.user;

import com.delay.domain.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                roles);
    }

    public User getCurrentUser() {
        Principal principal = getPrincipal();
        if ( principal!= null) {
            return userService.findByUsername(principal.getName());
        }
        return null;
    }

    public Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getAuthUserName() {
        return getPrincipal().getName();
    }

    /** Получить роль пользователя */
    @SuppressWarnings("unchecked")
    public String getAuthUserRole() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.iterator().hasNext()) {
            return authorities.iterator().next().getAuthority();
        }
        return null;
    }
}
