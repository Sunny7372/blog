package com.blogapp12.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min=3, message= "Title should be atleast 3 character")
    private String title;
    @NotEmpty
    @Size(min=3, message="Description should be atleast three characters")
    private String description;
    private String content;

}
