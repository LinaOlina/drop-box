package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.user.User;
import com.lina.individuelluppgift.user.UserRepository;
import com.lina.individuelluppgift.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public FolderController(FolderService folderService, UserService userService, UserRepository userRepository) {
        this.folderService = folderService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder, Principal principal) {
        Optional<User> currentUserOptional = userRepository.findByUsername(principal.getName());
        if (currentUserOptional.isPresent()) {
            User currentUser = currentUserOptional.get();
            Folder result = folderService.createFolder(folder, currentUser);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            // Handle the case where the user is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    };

}