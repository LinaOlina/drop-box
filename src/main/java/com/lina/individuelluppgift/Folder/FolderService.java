package com.lina.individuelluppgift.Folder;

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
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }


    public Folder createFolder(Folder folder, User user) {
       folder.setUser(user);
       return folderRepository.save(folder);
    }

    public boolean folderExists(Integer folderId){
        return folderRepository.existsById(folderId);
    }

    public void addFileToFolder(Integer folderId, String fileName) {
        //Hämta mapp
        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Folder not found"));

        File file = new File();
        file.setFile_name(fileName);
        file.setFolder(folder);

        fileRepository.save(file);
        List<File> files = folder.getFiles();
        files.add(file);
        folder.setFiles(files);

        folderRepository.save(folder);
    }
}
