package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.exception.FolderNotFoundException;
import com.lina.individuelluppgift.file.File;
import com.lina.individuelluppgift.file.FileRepository;
import com.lina.individuelluppgift.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
    }


    public Folder findFolderById(Integer folderId, User user) {
        Folder folder = folderRepository.findFolderByIdAndUser(folderId, user)
                .orElseThrow(() -> new FolderNotFoundException("Could not find folder with id " + folderId));

        return folder;
    }

    public Folder createFolder(Folder folder, User user) {
       folder.setUser(user);
       return folderRepository.save(folder);
    }

    public boolean folderExists(Integer folderId){
        return folderRepository.existsById(folderId);
    }



    public Folder getFolderById(Integer folderId) {
        return folderRepository.findFolderByIdWithFiles(folderId)
                .orElseThrow(() -> new FolderNotFoundException("Folder with id " + folderId + " could not be found."));
    }

    public void deleteFolder(Integer folderId, Authentication authentication) {
        String username = authentication.getName();
        Folder folder = (Folder) folderRepository.findFolderByIdAndUsername(folderId, username)
                        .orElseThrow(() -> new FolderNotFoundException("Folder with id " + folderId + " could not be found for user " + username));
        folderRepository.delete(folder);
    }


    public List<File> getFilesFromFolder(Integer folderId) {

        return folderRepository.findFilesByFolderId(folderId);
    }
}

