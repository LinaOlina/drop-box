package com.lina.individuelluppgift.file;

import com.lina.individuelluppgift.Folder.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FolderService folderService;
    private final FileService fileService;

    public FileController(FolderService folderService, FileService fileService) {
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @PostMapping("/upload/{folderId}")
    public ResponseEntity<String> uploadFile(
            @PathVariable Integer folderId,
            @RequestParam("file") MultipartFile file) {

        try {
            // Check if the folder exists
            if (!folderService.folderExists(folderId)) {
                return ResponseEntity.badRequest().body("Folder not found");
            }

            // Store the file and update the Folder entity
            String fileName = fileService.storeFile(file, folderId);
            folderService.addFileToFolder(folderId, fileName);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }
}
