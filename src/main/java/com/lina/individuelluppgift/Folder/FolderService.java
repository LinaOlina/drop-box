package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }


    public Folder createFolder(Folder folder, User user) {
       folder.setUser(user);
       return folderRepository.save(folder);
    }
}
