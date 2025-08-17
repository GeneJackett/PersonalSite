package com.personalsite.jackson.controller;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityReturnValueHandler;

import com.personalsite.jackson.model.User;
import com.personalsite.jackson.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //get all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    //request sent to db to remove record
    //print out success repsonse after
    //what happens if we call the same delete request and no record matches?
    //
    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
            if(userRepository.existsById(id)== true){
                userRepository.deleteById(id);
                System.out.println("we made it");
                return ResponseEntity.noContent().build();
            }else{
                 System.out.println("we didn't make it");
                return ResponseEntity.notFound().build();
            }
    } 

    //Create user, no frontend to pull values from angular, will have to come back and dynamically 
    // 
    //how do we check if the indexing is off or not? Does the generated number in the object know this?
    //yes it's smart, indexing is automatically handled.
    @PostMapping("/create/user")
        public User createUser(){
            User newUser = new User();
            newUser.setFirstName("Matt");
            newUser.setLastName("Johnson");
            newUser.setEmailID("matt.johnson@gmail.com");
            return userRepository.save(newUser);
        }

    // still no way of pulling dynamic fields from the frontend. will have to come back and update as needed.
    @PutMapping("/update/user/{id}")
        public  ResponseEntity<Void> updateUser(@PathVariable Long id){
            if(userRepository.existsById(id)== true){
            User existingUser = new User();
            existingUser.setId(id);
            existingUser.setFirstName("Matthew");
            existingUser.setLastName("Jackson");
            existingUser.setEmailID("matthew.jackson@gmail.com");
            userRepository.save(existingUser);
            return ResponseEntity.ok().build();
            }else{
                System.out.println("user doesn't exist...");
                return ResponseEntity.notFound().build();
            }
        }
    
}
