package by.bstu.po15.ats.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tickets")
public class Tickets
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Идентификатор билетной записи

    @Column(nullable = false)
    private Long user_id;   //  Идентификатор пользователя

    @Column(nullable = false)
    Long Poezd_places_id;   //  Идентификатор вагона в поезде

    @Column(nullable = false)
    private BigDecimal cost;        //  Цена билета

    @Column(nullable = false)
    private Long count;

}
