package com.lina.individuelluppgift.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lina.individuelluppgift.Folder.Folder;
import com.lina.individuelluppgift.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class File {


    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Integer id;

    private String name;
    private String type;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "folder_id", nullable = false )
    private Folder folder;

    public File(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;

    }


}
