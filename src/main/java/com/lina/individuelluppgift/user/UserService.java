package com.lina.individuelluppgift.user;

import com.lina.individuelluppgift.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService   {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public void deleteUser(Integer id){
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found."));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }


    public Integer findUserIdByUsername(String username) {

        return userRepository.findUserIdByUsername(username);
    }


}
