package by.bstu.po15.ats.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime ;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Poezd_placesDto
{   private Long id;    // Идентификатор поезда

    private Long poezd_id;   // идентификатор поезда

    private Long sost_id;   // идентификатор вагона в таблице составов

    private Long free_places;   // количество свободных мест в вагоне

    private BigDecimal cost;        //  Стоимость места в вагоне

    private String vgname;   // Название типа вагона

    private Long vgnum;   // Номер вагона
}
