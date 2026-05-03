package by.bstu.po15.ats.web.entity;
import lombok.*;

@Getter
@Setter
public class newPassData
{   public String email;    // логин пользователя
    public String oldpass;  // старый пароль
    public String newpass1; // новый пароль 1
    public String newpass2; // новый пароль 2

    public newPassData(String email)
    {   setEmail(email);
    }
}
