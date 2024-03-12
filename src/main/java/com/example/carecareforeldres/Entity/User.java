package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String passwd;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roless = new ArrayList<>() ;
        for (Role authority : roles ) {
            if (authority !=null)
                roless.add(new SimpleGrantedAuthority(authority.getName().name()));
            else
                System.out.println("----- U have no AUtority Bro ----");
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
    private Set<Role> roles;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="medecin")
    List<Rdv>rdvs;
    @OneToOne(cascade = CascadeType.ALL)
    Ambulance ambulance;
}
