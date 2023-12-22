package com.lina.individuelluppgift.file;

import com.lina.individuelluppgift.Folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    File findFileById(Integer fileId);


    Optional<Object> findByIdAndFolderId(Integer fileId, Integer folderId);
}
