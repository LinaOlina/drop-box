package com.lina.individuelluppgift.file;


import com.lina.individuelluppgift.Folder.Folder;
import com.lina.individuelluppgift.Folder.FolderRepository;
import com.lina.individuelluppgift.exception.SizeTooLargeException;
import com.lina.individuelluppgift.user.User;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ByteArrayResource;
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
    private final FolderRepository folderRepository;

    public FileService(FileRepository fileRepository, FolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }
    private final String uploadDir = "your-upload-directory"; // Replace with your actual upload directory


    @Transactional
    public String storeFile(MultipartFile file, Integer folderId) throws IOException {

        if(isSizeValid(file)) {
            Folder folder = folderRepository.findById(folderId)
                    .orElseThrow();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File newFile = new File(fileName, file.getContentType(), file.getBytes());
            newFile.setFolder(folder);
            fileRepository.save(newFile);
            return newFile.getFile_name();
        } else {
            throw new SizeTooLargeException("File size is too big.");
        }
        //ByteArrayResource resource = new ByteArrayResource(newFile.getData());




    }


    public boolean isSizeValid(MultipartFile file) {
        long fileSize = file.getSize();
        long maxSize =  2* 1024 * 1024;

        return fileSize <= maxSize;
    }

}


