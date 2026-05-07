package by.bstu.po15.ats.web.dto;

import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.entity.VgTypes;
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
public class SostavDto
{
    private Long id;    // Идентификатор описания типа вагона в составе

    @NotEmpty(message = "Маршрут не может быть пустым")
   Long marshrut_id; // Ссылка на родительскую таблицу marshrut

    @NotEmpty(message = "Тип вагона не может быть пустым")
    private Long vgid; // Сссылка на список типов вагона

    @NotEmpty(message = "Номер вагона не может быть пустым")
    Long numer;     //  Номер вагона в составе

    @NotEmpty(message = "Цена места в составе не может быть пустой")
    private BigDecimal cost;        //  Стоимость места в вагоне
}
