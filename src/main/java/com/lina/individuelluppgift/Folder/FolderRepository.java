package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.file.File;
import com.lina.individuelluppgift.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

    Optional<Folder> findFolderById(Integer folderId);
    @Query("SELECT f FROM Folder f LEFT JOIN FETCH f.files WHERE f.id = :folderId")
    Optional<Folder> findFolderByIdWithFiles(@Param("folderId") Integer folderId);

    @Query("SELECT f.files FROM Folder f WHERE f.id = :folderId")
    List<File> findFilesByFolderId(@Param("folderId") Integer folderId);

    //List<Folder> findFoldersByUser(User user);
}
