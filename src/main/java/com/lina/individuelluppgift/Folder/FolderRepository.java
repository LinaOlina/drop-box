package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {


    @Query("SELECT f FROM Folder f LEFT JOIN FETCH f.files WHERE f.id = :folderId")
    Optional<Folder> findFolderByIdWithFiles(@Param("folderId") Integer folderId);

    @Query("SELECT f.files FROM Folder f WHERE f.id = :folderId")
    List<File> findFilesByFolderId(@Param("folderId") Integer folderId);

    @Query("SELECT f FROM Folder f WHERE f.id = :folderId AND f.user.username = :username")
    Optional<Object> findFolderByIdAndUsername(@Param("folderId") Integer folderId, @Param("username") String username);


}
