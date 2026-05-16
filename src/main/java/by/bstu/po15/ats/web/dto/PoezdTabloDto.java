package by.bstu.po15.ats.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Getter
public class PoezdTabloDto
{
    private Long id;    // Идентификатор поезда

    private String nomer;   // номер поезда

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private ZonedDateTime datetime;    //  Дата и время отправление

    private String marshrut;   //  Название маршрута

    private Long platsckart;    // Свободно в плацкарте

    private Long kupe;    // Свободно в купе

    private Long common;    // Свободно в общем

    private Long spalnyi;    // Свободно в спальном

    private Long sitten;    // Свободно в сидячем
}