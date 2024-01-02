package com.lina.individuelluppgift.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {



    @Query("SELECT f FROM File f WHERE f.id = :fileId AND f.folder.user.username = :username")
    Optional<File> findFileByIdAndUsername(@Param("fileId") Integer fileId, @Param("username") String username);


    Optional<Object> findByIdAndFolderId(Integer fileId, Integer folderId);
}
