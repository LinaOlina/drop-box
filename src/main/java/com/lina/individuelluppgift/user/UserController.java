package com.lina.individuelluppgift.user;

import com.lina.individuelluppgift.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, JwtService jwtService, UserRepository userRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

@Transactional
    @GetMapping("/getUser/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id, HttpServletRequest request){

        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        Integer currentUserId = userService.findUserIdByUsername(username);

        if (!currentUserId.equals(id)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this user");
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
