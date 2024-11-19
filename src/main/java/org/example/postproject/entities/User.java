package org.example.postproject.entities;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.postproject.entities.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
/**
 * user table yaratib berish uchun kerak bolgan class
 */
public class User extends BaseEntity implements UserDetails {

    private String username;
    private String password;

    /**
     * bu yerda hohlasak userlarga role yoki permission qoshsak boladi
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

}
