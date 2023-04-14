package com.isep.acme.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    @Email
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<Role> authorities = new HashSet<>();

    @Column(unique = true)
    @NonNull
    @Size(min = 9, max = 9)
    private String nif;

    @Column
    @NonNull
    private String morada;

    /*
     * @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     * private List<Review> review = new ArrayList<Review>();
     */

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final String username, final String password, final String fullName, final String nif,
            final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
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
