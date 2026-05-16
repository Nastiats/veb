package by.bstu.po15.ats.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;

@Getter
@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Table(name="poezdtablo")
public class PoezdTablo
{   @Id
    private Long id;    // Идентификатор поезда

    @Column(nullable = false)
    private String nomer;   // номер поезда

    @Column(nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime  datetime;    //  Дата и время отправление

    @Column(nullable = false)
    private String marshrut;   //  Название маршрута

    @Column(nullable = false)
    private Long platsckart;    // Свободно в плацкарте

    @Column(nullable = false)
    private Long kupe;    // Свободно в купе

    @Column(nullable = false)
    private Long common;    // Свободно в общем

    @Column(nullable = false)
    private Long spalnyi;    // Свободно в спальном

    @Column(nullable = false)
    private Long sitten;    // Свободно в сидячем

}
