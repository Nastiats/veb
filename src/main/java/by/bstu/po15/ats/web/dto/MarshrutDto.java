package by.bstu.po15.ats.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarshrutDto
{
    private Long id;    // Идентификатор маршрута

    @NotEmpty(message = "Номер маршрута не может быть пустым")
    private String numer;        //  Номер маршрута

    @NotEmpty(message = "Начальная станция не может быть пустой")
    private String frm;        //  Начальная станция маршрута

    @NotEmpty(message = "Конечная станция не может быть пустой")
    private String toto;       //  Конечная станция маршрута
}
