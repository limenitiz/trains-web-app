package limenitiz.study.restapi.model;

import limenitiz.study.restapi.entity.PassengerEntity;
import limenitiz.study.templates.TemplateDto;
import limenitiz.study.restapi.entity.PlaceEntity;
import lombok.*;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Place implements TemplateDto<PlaceEntity> {
    private Long id;
    private Integer number;
    private PlaceClass placeClass;
    private Passenger passenger;

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
                .build();
    }
}

