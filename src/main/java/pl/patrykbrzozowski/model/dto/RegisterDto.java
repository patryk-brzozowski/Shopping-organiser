package pl.patrykbrzozowski.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull
    private String userName;
    @NotNull
    private String email;
    @Min(6)
    private String password;
    @Min(6)
    private String confirm_password;
}
