package org.co.connecthub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private String categoryAbout;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    List<Post> posts=new ArrayList<>();

}
