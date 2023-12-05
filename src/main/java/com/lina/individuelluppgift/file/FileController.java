package com.lina.individuelluppgift.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload/{folderId}")
    public ResponseEntity<String> uploadFile(
            @PathVariable Integer folderId,
            @RequestParam("file") MultipartFile file) {

    }
}
