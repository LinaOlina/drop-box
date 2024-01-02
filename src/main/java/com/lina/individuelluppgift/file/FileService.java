package com.lina.individuelluppgift.file;


import com.lina.individuelluppgift.Folder.Folder;
import com.lina.individuelluppgift.Folder.FolderRepository;
import com.lina.individuelluppgift.exception.FolderOrFileNotFoundException;
import com.lina.individuelluppgift.user.User;
import com.lina.individuelluppgift.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FileService {

    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    public FileService(FileRepository fileRepository, FolderRepository folderRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.userRepository = userRepository;
    }


    public File getFileInFolderById(Integer folderId, Integer fileId) {

        return (File) fileRepository.findByIdAndFolderId(fileId, folderId)
                .orElseThrow(() -> new FolderOrFileNotFoundException("Could not find folder or file."));
    }

    public ResponseEntity<String> storeFile(MultipartFile file, Integer folderId, Principal principal) throws IOException {

        Optional<User> currentUserOptional = userRepository.findByUsername(principal.getName());
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow();

        if(currentUserOptional.isPresent()) {
            User currentUser = currentUserOptional.get();


            String fileName = file.getOriginalFilename();
            File newFile = new File(fileName, file.getContentType(), file.getBytes());
            newFile.setFolder(folder);
            newFile.setUser(currentUser);

            fileRepository.save(newFile);
            return ResponseEntity.ok("Successfully uploaded: " + file.getOriginalFilename() +
                    " to folder with id: " + folderId + " on user " + currentUser.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }


    }


    public void deleteFile( Integer fileId, String username) {
        File file = fileRepository.findFileByIdAndUsername(fileId, username)
                        .orElseThrow(() ->  new FolderOrFileNotFoundException("Could not find file."));
        fileRepository.delete(file);

    }


    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }


}


