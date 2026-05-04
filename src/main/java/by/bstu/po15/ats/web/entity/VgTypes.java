package by.bstu.po15.ats.web.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vgtypes")
public class VgTypes    // Типы вагонов
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Идентификатор типа вагона

    @Column(nullable = false)
    private String name;        //  Название типа вагона

    @Column(nullable = false)
    private Long places;        //  количество мест в вагоне

    @Column(nullable = false)
    private String Short;       //  Обозначение типа вагона

    @Column(nullable = false,precision = 19, scale = 2)
    private BigDecimal cost;        //  Стоимость места в вагоне

}