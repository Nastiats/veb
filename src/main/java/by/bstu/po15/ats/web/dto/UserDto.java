package by.bstu.po15.ats.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto
{   private Long id;    // Идентификатор пользователя

    @NotEmpty
    private String name; // Логин пользователя

    @NotEmpty(message = "Email не может быть пустым")
    @Email
    private String email; // email пользователя (Он не может быть пустым)

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password; // пароль пользователя (Он не может быть пустым)

    @NotEmpty(message = "Вопрос  для восстановления не может быть пустым")
    private String usquery; // Вопрос для восстановления пароля

    @NotEmpty(message = "Ответ для восстановления пароля не может быть пустым")
    private String usanswer; // Ответ для восстановления паррля

}