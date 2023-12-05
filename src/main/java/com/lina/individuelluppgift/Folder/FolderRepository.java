package com.lina.individuelluppgift.Folder;

import com.lina.individuelluppgift.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {



}
