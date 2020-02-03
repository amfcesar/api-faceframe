package br.com.anodes.apifaceframe.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String author;
    private String text;
    private String link;
    private String title;
    private UserDto user;
    private List<CommentsDto> commentsDtos;


    @NotEmpty(message = "Text não pode ser vazio.")
    @Length(min = 5, max = 200, message = "Text deve conter entre 5 e 200 caracteres.")
    public String getText(){
        return text;
    }


    public UserDto getUser() {
        return user;
    }
}
