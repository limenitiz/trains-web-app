package limenitiz.study.app.model;

import limenitiz.study.app.entity.TrainExpressEntity;
import limenitiz.study.templates.IDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@SuperBuilder
@Getter @Setter
//@NoArgsConstructor
@AllArgsConstructor
public class TrainExpress
        extends AbstractTrain
        implements IDto<TrainExpressEntity> {

    public TrainExpress(Long id, String number,
                        String arrivalCity, String departureCity,
                        LocalDateTime arrivalTime, LocalDateTime departureTime,
                        List<Place> places) {
        super(id, number,
                arrivalCity, departureCity,
                arrivalTime, departureTime,
                places);
    }

    @Override
    public TrainExpressEntity toEntity() {
        var e = new TrainExpressEntity(
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
