package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String region;
    private Integer nbr_tentatives;
    private String numphone;
    private Date sleep_time;
    private Boolean enabled;
    private String email;
     private String passwd;
     private boolean mfaEnabled;
    private String secret;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roless = new ArrayList<>() ;
        for (Role authority : roles ) {
            if (authority !=null&& authority.getName() != null)
                roless.add(new SimpleGrantedAuthority(authority.getName().name()));
            else
                System.out.println("----- U have no role ----");
        }
        return roless;
    }
    public Set<Role> getAuthFromBase(){
        return this.roles;
    }//role

    @Override
    public String getPassword() {
        return passwd;
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
    @OneToMany(mappedBy = "UserAuth" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Role> roles ;
    //////////////////// ahiya il mmethode//////////// ray matkhdimch
    //  public Set<Role> getRoles() {return roles != null ? roles : Collections.emptySet();}
   
}
