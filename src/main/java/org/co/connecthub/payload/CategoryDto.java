package org.co.connecthub.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private Integer categoryId;
    private String categoryName;
    private String categoryAbout;
    private List<PostDto> posts=new ArrayList<>();
}

