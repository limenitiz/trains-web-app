package limenitiz.study.app.model;


import limenitiz.study.templates.IDto;
import limenitiz.study.app.entity.TrainEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter @Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Train
        extends AbstractTrain
        implements IDto<TrainEntity> {

    public Train(Long id, String number,
                 String arrivalCity, String departureCity,
                 LocalDateTime arrivalTime, LocalDateTime departureTime,
                 List<Place> places) {
        super(id, number,
                arrivalCity, departureCity,
                arrivalTime, departureTime,
                places);
    }

    @Override
    public TrainEntity toEntity() {
        var e = new TrainEntity(
                null,
                number,
                arrivalCity,
                departureCity,
                arrivalTime,
                departureTime
        );

        if (this.places == null) {
            this.places = new LinkedList<>();
        }

        e.getPlaces().addAll(places.stream()
                .map(Place::toEntity)
                .toList()
        );

        return e;
    }
}

