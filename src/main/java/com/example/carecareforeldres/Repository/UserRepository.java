package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

Optional<User> findByEmail(String email);
@Query("select u from User u where u.email=:email")
    User fINDMail(String email);
}
