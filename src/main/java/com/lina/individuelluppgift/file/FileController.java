package com.lina.individuelluppgift.file;

import com.lina.individuelluppgift.Folder.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FolderService folderService;
    private final FileService fileService;

    @Autowired
    public FileController(FolderService folderService, FileService fileService) {
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @PostMapping("/upload/{folderId}")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Integer folderId) {

        try {
            // Check if the folder exists
            if (!folderService.folderExists(folderId)) {
                return ResponseEntity.badRequest().body("Folder not found");
            }

            // Store the file and update the Folder entity
            fileService.storeFile(file, folderId);
            String message = "Successfully uploaded: " + file.getOriginalFilename();

            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }
}
