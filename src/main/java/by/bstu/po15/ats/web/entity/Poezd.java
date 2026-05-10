package by.bstu.po15.ats.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime ;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="poezd")
public class Poezd
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Идентификатор поезда

    @Column(nullable = false)
    private String nomer;   // номер поезда

    @Column(nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    private ZonedDateTime  datetime;    //  Дата и время отправление

    @Column(nullable = false)
    private Long marshrut_id;   //  Идентификтора маршрута

    private String marshrut_name;   //  Название маршрута
    private String edit_datetime;   // Временно, для редактирования даты-времени в Браузере
}
