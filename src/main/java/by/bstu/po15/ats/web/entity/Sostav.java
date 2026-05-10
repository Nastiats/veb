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
@Table(name="sostav")
public class Sostav
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Идентификатор описания типа вагона в составе

    @Column(nullable = false)
    private Long marshrut_id; // Ссылка на родительскую таблицу marshrut

    @Column(nullable = false)
    private Long vgid; // Сссылка на список типов вагона

    @Column(nullable = false)
    private Long numer;     //  Номер вагона в составе

    @Column(nullable = false,precision = 19, scale = 2)
    private BigDecimal cost;        //  Стоимость места в вагоне

    private String vtype;   // Название типа вагона
}
