package br.com.anodes.apifaceframe.dtos;


import br.com.anodes.apifaceframe.validation.ValidPassword;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String cpf;

    @ValidPassword
    private String password;

    @NotEmpty(message = "Nome não pode ser vazio.")
    @Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
    public String getName() {
        return name;
    }

    @NotEmpty(message = "Email não pode ser vazio.")
    @Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
    @Email(message="Email inválido.")
    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    @CPF(message = "Cpf invalido")
    public String getCpf(){
        return cpf;
    }
}
