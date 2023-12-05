package com.lina.individuelluppgift.file;


import com.lina.individuelluppgift.Folder.Folder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    private final String uploadDir = "your-upload-directory"; // Replace with your actual upload directory


    public String storeFile(MultipartFile file, Integer folderId) {

        // Process and store the file
        // You can implement your logic here, for example, saving the file to a specific directory

        // Save file information to the database
        File newFile = new File();
        newFile.setFile_name(file.getOriginalFilename());
        // ... (other file information)

        fileRepository.save(newFile);

        return newFile.getFile_name();

    }
    }


