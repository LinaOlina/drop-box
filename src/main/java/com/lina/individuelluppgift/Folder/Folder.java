package com.lina.individuelluppgift.Folder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lina.individuelluppgift.file.File;
import com.lina.individuelluppgift.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folders")
public class Folder {
    @Id
    @GeneratedValue
    @Column(name = "folder_id", nullable = false)
    private Integer id;

    @Column(name = "folder_name")
    private String folder_name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false )
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

    public Folder(Integer id) {
        this.id = id;
    }




}
