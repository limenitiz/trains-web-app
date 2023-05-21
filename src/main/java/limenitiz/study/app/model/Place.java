package limenitiz.study.app.model;

import limenitiz.study.app.entity.PassengerEntity;
import limenitiz.study.templates.IDto;
import limenitiz.study.app.entity.PlaceEntity;
import lombok.*;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Optional;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Place implements IDto<PlaceEntity> {
    private Long id;
    private Integer number;
    private PlaceClass placeClass;
    private Passenger passenger;
    private Double price;

    public String getPriceStr() {
        return String.format("%,.2f Р",
                Optional.ofNullable(price)
                        .orElse(0.0));
    }

    public String getFullName() {
        if (passenger == null) {
            return "";
        }

        return Optional.ofNullable(passenger
                        .getFirstName())
                        .orElse("")
                .concat(" ")
                .concat(Optional.ofNullable(passenger
                        .getSecondName())
                        .orElse(""));
    }

    public boolean isAvailable() {
        return passenger == null;
    }

    @Override
    public PlaceEntity toEntity() {
        PassengerEntity passengerEntity = null;
        if (passenger != null) {
            passengerEntity = passenger.toEntity();
        }

        return PlaceEntity.builder()
                .number(number)
                .placeClass(placeClass)
                .passenger(passengerEntity)
                .price(Optional.ofNullable(price)
                        .orElse(0.0))
                .build();
    }
}

