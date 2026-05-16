package by.bstu.po15.ats.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="poezd_places")
public class Poezd_places
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Идентификатор поезда

    @Column(nullable = false)
    private Long poezd_id;   // идентификатор поезда

    @Column(nullable = false)
    private Long sost_id;   // идентификатор вагона в таблице составов

    @Column(nullable = false)
    private Long free_places;   // количество свободных мест в вагоне

    @Column(nullable = false,precision = 19, scale = 2)
    private BigDecimal cost;        //  Стоимость места в вагоне

    @Column(nullable = false)
    private String vgname;   // Название типа вагона

    @Column(nullable = false)
    private Long vgnum;   // Номер вагона

}
