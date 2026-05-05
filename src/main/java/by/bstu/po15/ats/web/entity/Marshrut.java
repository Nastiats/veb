package by.bstu.po15.ats.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="marshrut")
public class Marshrut    // Типы вагонов
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Идентификатор типа вагона

    @Column(nullable = false)
    private String numer;        //  Номер маршрута

    @Column(nullable = false)
    private String frm;        //  Начальная станция

    @Column(nullable = false)
    private String toto;       //  Конечная станция

 }
