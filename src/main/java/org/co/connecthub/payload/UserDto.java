package org.co.connecthub.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.co.connecthub.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String about;
    //private List<PostDto> posts=new ArrayList<>();
}
