package br.com.anodes.apifaceframe.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

    private Long id;
    private Long postId;
    private String text;

    @NotEmpty(message = "Text não pode ser vazio.")
    @Length(min = 5, max = 200, message = "Text deve conter entre 5 e 200 caracteres.")
    public String getText(){
        return text;
    }

}
