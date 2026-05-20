package by.bstu.po15.ats.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Getter
public class UserTicketsDto
{
    private Long Id;    // Идентификатор билетной записи

    private Long user_id;   //  Идентификатор пользователя

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private ZonedDateTime datetime;    //  Дата и время отправление

    private String nomer;   // номер поезда

    private String marshrut;   //  Название маршрута

    private Long vgnum;     //  Номер вагона
    private String vgname;  //  Название типа вагона

    private Long count;     //  Количество билетов

    private BigDecimal cost;        // Цена места в билете
}
