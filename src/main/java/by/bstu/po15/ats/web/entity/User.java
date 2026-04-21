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
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL) //This FetchType.Eager is to ensure that hibernate loads All the jpa entities associates with main jpa entitiy while initiating them.
    @JoinTable(
            name="user_roles",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")} //For 3rd table to have 2 tables primary keys.
    )
    private List<Role> roles = new ArrayList<>();   //Cascading is the option whenver we are changing any record of user, then respective record for role

    // Default constructor is needed for JPA
}