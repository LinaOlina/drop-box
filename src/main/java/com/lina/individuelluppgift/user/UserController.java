package com.lina.individuelluppgift.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    @PostMapping("/register")
    public void registerNewUser(@Valid @RequestBody User user) throws IllegalAccessException {
        userService.registerUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody User user){
        return  userService.login(user.getUsername(),user.getPassword());



    }

     */

}
