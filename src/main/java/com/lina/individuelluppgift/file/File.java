package com.lina.individuelluppgift.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lina.individuelluppgift.Folder.Folder;
import com.lina.individuelluppgift.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class File {

    // id, name, byte = data, size

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Integer id;

    private String name;
    private String type;

    @Lob
    @Column(name = "data", nullable = false)
    @JsonSerialize(using = ByteArraySerializer.class)
    private String data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", nullable = false )
    private Folder folder;

    public File(String name, String type, String data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("image_id", this.id);
        result.put("filename", this.name);
        return result;
    }
}
