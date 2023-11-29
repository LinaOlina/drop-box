package com.lina.individuelluppgift.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;
    private Map<String, User> users = new HashMap<>();
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void registerUser(User user) throws IllegalAccessException{
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalAccessException("Email is already taken, please use another one.");

        }
        userRepository.save(user);
        System.out.println("Sucessfully saved user: " + user);

    }

    public void deleteUser(String id){
        UUID uuid = UUID.fromString(id);
        userRepository.deleteById(uuid);
    }


}
