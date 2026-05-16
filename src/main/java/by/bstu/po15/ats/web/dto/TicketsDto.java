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
public class TicketsDto
{   private Long id;        //  Идентификатор билетной записи

    @NotEmpty(message = "Идентификатор пользователя не может быть пустым")
    private Long user_id;   //  Идентификатор пользователя

    @NotEmpty(message = "Идентификатор вагоа в поезде не может быть пустым")
    Long Poezd_places_id;   //  Идентификатор вагона в поезде

    @NotEmpty(message = "Цена билета не может быть пустой")
    private BigDecimal cost;        //  Цена билета

    @NotEmpty(message = "Количество билетов не может быть пустым")
    private Long count;
}
