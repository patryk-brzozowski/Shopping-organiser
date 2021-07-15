package pl.patrykbrzozowski.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull
    private String userName;
    @NotNull
    private String email;
    @Size(min = 6)
    private String password;
    @Size(min = 6)
    private String confirm_password;
}
