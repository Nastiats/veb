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
public class VgTypesDto
{
    private Long id;    // Идентификатор типа вагона

    @NotEmpty(message = "Название типа не может быть пустым")
    private String name;        //  Название типа вагона

    @NotEmpty(message = "Количество мест в вагоне не может быть пустым")
    private Long places;        //  количество мест в вагоне

    @NotEmpty(message = "Обозначение типа не может быть пустым")
    private String Short;       //  Обозначение типа вагона

    @NotEmpty(message = "Стоимость места в вагоне не может быть пустым")
    private BigDecimal cost;        //  Стоимость места в вагоне
}
