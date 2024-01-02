package com.lina.individuelluppgift.file;

import lombok.Getter;

@Getter
public class FileDTO {
    private Integer id;
    private final String name;

    public FileDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
