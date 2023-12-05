package com.lina.individuelluppgift.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lina.individuelluppgift.Folder.Folder;
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
@Table(name = "_files")
public class File {

    // id, name, byte = data, size

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Integer id;

    private String file_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", nullable = false )
    @JsonIgnore
    private Folder folder;

}
