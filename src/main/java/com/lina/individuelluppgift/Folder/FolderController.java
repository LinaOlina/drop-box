package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.user.User;
import com.lina.individuelluppgift.user.UserRepository;
import com.lina.individuelluppgift.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;
    private final UserService userService;
    private final UserRepository userRepository;

    private final FolderRepository folderRepository;
    @Autowired
    public FolderController(FolderService folderService, UserService userService, UserRepository userRepository, FolderRepository folderRepository) {
        this.folderService = folderService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
    }

    @GetMapping("/getFolder/{folderId}")
    public ResponseEntity<Folder> getFolderById(@PathVariable Integer folderId){
        Folder folder = folderRepository.findFolderById(folderId);

        return new ResponseEntity<>(folder, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{folderId}")
    public ResponseEntity<HttpStatus> deleteFolder(@PathVariable Integer folderId){
        folderService.deleteFolder(folderId);
        return new ResponseEntity<>(HttpStatus.OK);
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

    }




}