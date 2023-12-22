package com.lina.individuelluppgift.file;

import com.lina.individuelluppgift.Folder.FolderService;
import com.lina.individuelluppgift.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/{folderId}/files/{fileId}")
    public ResponseEntity<File> getFileInFolderById(
            @PathVariable Integer folderId,
            @PathVariable Integer fileId) {

        try {
            File file = fileService.getFileInFolderById(folderId, fileId);

            if (file != null) {
                return new ResponseEntity<>(file, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllFiles")
    public List<File> getAllFiles(){

        return fileService.getAllFiles();

    }





    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<HttpStatus> deleteFile(@PathVariable Integer fileId){
        fileService.deleteFile(fileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping("/upload/{folderId}")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Integer folderId,
            Principal principal) {



        try {
            if (!folderService.folderExists(folderId)) {
                return ResponseEntity.badRequest().body("Folder not found");
            }

           return fileService.storeFile(file, folderId, principal);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file");
        }

    }


}
