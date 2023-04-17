package limenitiz.study.restapi.model;

import limenitiz.study.restapi.entity.TrainEntity;
import limenitiz.study.restapi.entity.TrainExpressEntity;
import limenitiz.study.templates.IDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainExpress
        implements IDto<TrainExpressEntity> {
    private Long id;
    private String name;
    private String number;
    private String arrivalCity;
    private String departureCity;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private List<Place> places = new LinkedList<>();

    @Override
    public TrainExpressEntity toEntity() {
        var e = new TrainExpressEntity(
                null,
                name,
                number,
                arrivalCity,
                departureCity,
                arrivalTime,
                departureTime
        );

        e.getPlaces().addAll(places.stream()
                .map(Place::toEntity)
                .toList()
        );

        return e;
    }
}
