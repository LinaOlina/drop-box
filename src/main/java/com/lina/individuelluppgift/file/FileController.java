package com.lina.individuelluppgift.file;

import com.lina.individuelluppgift.Folder.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FolderService folderService;
    private final FileService fileService;

    private final FileRepository fileRepository;


    @Autowired
    public FileController(FolderService folderService, FileService fileService, FileRepository fileRepository) {
        this.folderService = folderService;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId) {
        Optional<File> fileOptional = fileRepository.findById(fileId);

        if (fileOptional.isPresent()) {
            File file = fileOptional.get();


            ByteArrayResource resource = new ByteArrayResource(file.getData());

            MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
            System.out.println();

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getId() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public List<FileDTO> getAllFiles(){
        List<File> files = fileService.getAllFiles();
        return files.stream()
                .map( x-> {
                    FileDTO newFileDTO = new FileDTO(x.getId(), x.getName());
                    return newFileDTO;
                })
                .collect(Collectors.toList());

    }





    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<HttpStatus> deleteFile(@PathVariable Integer fileId, Authentication authentication){
        String username = authentication.getName();
        fileService.deleteFile(fileId, username);
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
