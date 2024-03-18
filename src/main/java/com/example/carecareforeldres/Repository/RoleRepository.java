package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Role;
import com.example.carecareforeldres.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

//@Query("select u.roles from User u where u.roles=:role")
  //  User findobjet(Role role);
}
