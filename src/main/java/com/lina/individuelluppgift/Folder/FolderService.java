package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.exception.FolderNotFoundException;
import com.lina.individuelluppgift.file.File;
import com.lina.individuelluppgift.file.FileRepository;
import com.lina.individuelluppgift.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Folder getFolder(Integer folderId){
        return folderRepository.findById(folderId)
                .orElseThrow(() -> new FolderNotFoundException("Folder with id: " + folderId + " does not exist"));
    }

    public List<Folder> findFolderByUser(User user) {
        return folderRepository.findFolderByUser(user);
    }
}
