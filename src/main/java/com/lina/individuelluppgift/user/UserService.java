package com.lina.individuelluppgift.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService   {

    private final UserRepository userRepository;

    private Map<String, User> users = new HashMap<>();
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }










    /*

    public String login(String username, String password){
        boolean isValid = userRepository.isValidUser(username,password);

        if(isValid){
            User user = findUserByUsername(username);
            return jwtHandler.generateToken(user);
        }
        else{
            return "User doesn't exist!";
        }
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

     */


}
