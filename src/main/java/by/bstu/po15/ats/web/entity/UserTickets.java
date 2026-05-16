package by.bstu.po15.ats.web.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usertickets")
public class UserTickets
{   @Id
    private Long user_id;   //  Идентификатор пользователя

    @Column(nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime datetime;    //  Дата и время отправление

    @Column(nullable = false)
    private String nomer;   // номер поезда

    @Column(nullable = false)
    private String marshrut;   //  Название маршрута

    @Column(nullable = false)
    private Long vgnum;     //  Номер вагона

    @Column(nullable = false)
    private String vgname;  //  Название типа вагона

    @Column(nullable = false)
    private Long count;     //  Количество билетов

    @Column(nullable = false)
    private BigDecimal cost;        // Цена места в билете
}
