package pl.patrykbrzozowski.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull
    @Size(min = 3, max = 25)
    private String userName;
    @NotNull
    @Email
    private String email;
    @Size(min = 6, max = 60)
    private String password;
    @Size(min = 6, max = 60)
    private String confirm_password;

}
