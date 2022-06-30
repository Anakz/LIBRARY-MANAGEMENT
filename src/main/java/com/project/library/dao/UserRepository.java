package com.project.library.dao;

import com.project.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password); //For Login
    User findByEmailAndCin(String email, String cin); //For Create

    Long countById(Long id);

    Long countByEmail(String email);

    Long countByCin(String cin);


    //List<User> findByEmailAndPassword(String email, String password);
    //boolean verifyByEmailAndPassword(String email, String password);
    //User getUserById(Long id);
}
