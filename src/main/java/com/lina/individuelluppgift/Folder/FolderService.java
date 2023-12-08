package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.exception.FolderNotFoundException;
import com.lina.individuelluppgift.file.File;
import com.lina.individuelluppgift.file.FileRepository;
import com.lina.individuelluppgift.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Folder createFolder(Folder folder, User user) {
       folder.setUser(user);
       return folderRepository.save(folder);
    }

    public boolean folderExists(Integer folderId){
        return folderRepository.existsById(folderId);
    }



    public Folder getFolderById(Integer folderId){
        return folderRepository.findFolderById(folderId);

    }

    public void deleteFolder(Integer folderId) {
        Folder folder = folderRepository.findFolderById(folderId);
        folderRepository.delete(folder);
    }
}

