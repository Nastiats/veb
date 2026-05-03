package by.bstu.po15.ats.web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // Идентификатор пользователя

    @Column(nullable = false)
    private String name;        // Логин пользователя

    @Column(nullable = false, unique = true)
    private String email;       // email пользователя

    @Column(nullable = false)
    private String password;    //  Пароль пользователя

    @Column(nullable = false)
    private String usquery;     // Вопрос для восстановления паррля

    @Column(nullable = false)
    private String usanswer;    // Ответ для пожтверждения подлинности

    // Список всех  ролей пользователя находится в таблице roles
    // Таблица user_roles содержит ссылки назначенных пользователею ролей

    //  JPA будет загружать весь список пользовательских ролей в список roles
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="user_roles",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")} //For 3rd table to have 2 tables primary keys.
    )
    private List<Role> roles = new ArrayList<>();   // Список ролей пользователя

}