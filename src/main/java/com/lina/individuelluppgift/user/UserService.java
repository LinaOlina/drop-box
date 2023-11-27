package com.lina.individuelluppgift.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
