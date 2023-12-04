package com.lina.individuelluppgift.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService   {
    /*
    private final UserRepository userRepository;
    private final JwtHandler jwtHandler;
    private Map<String, User> users = new HashMap<>();
    @Autowired
    public UserService(UserRepository userRepository, JwtHandler jwtHandler) {
        this.userRepository = userRepository;
        this.jwtHandler = jwtHandler;
    }



    public List<User> getUsers(){
        return userRepository.findAll();
    }

     */

    /*
    public void registerUser(User user) throws IllegalAccessException{
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null){
            throw new IllegalAccessException("Username is already taken, please use another one.");

        }
        userRepository.save(user);
        System.out.println("Sucessfully saved user: " + user);

    }



    public void deleteUser(String id){
        UUID uuid = UUID.fromString(id);
        userRepository.deleteById(uuid);
    }

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
