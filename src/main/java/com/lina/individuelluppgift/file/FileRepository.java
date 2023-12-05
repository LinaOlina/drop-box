package com.lina.individuelluppgift.file;

import com.lina.individuelluppgift.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    Optional<File> getfileById(Integer id);


}
