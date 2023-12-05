package com.lina.individuelluppgift.Folder;

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
@Table(name = "_folders")
public class Folder {
    @Id
    @GeneratedValue
    @Column(name = "folder_id", nullable = false)
    private Integer id;

    @Column(name = "folder_name")
    private String folder_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false )
    private User user;

    public Folder(Integer id) {
        this.id = id;
    }
}
