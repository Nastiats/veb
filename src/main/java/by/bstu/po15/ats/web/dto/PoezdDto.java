package by.bstu.po15.ats.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime ;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PoezdDto
{   private Long id;    // Идентификатор описания типа вагона в составе

    @NotEmpty(message = "Номер поезда не может быть пустым")
    private String nomer;   // номер поезда

    @NotEmpty(message = "Дата и время отправления не может быть пустым")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private ZonedDateTime  datetime;    //  Дата и время отправление

    @NotEmpty(message = "Маршрут не может быть пустым")
    Long marshrut_id; // Ссылка на родительскую таблицу marshrut

}
