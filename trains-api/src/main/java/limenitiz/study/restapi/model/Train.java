package limenitiz.study.restapi.model;


import limenitiz.study.templates.IDto;
import limenitiz.study.restapi.entity.TrainEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import lombok.*;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Train implements IDto<TrainEntity> {
    private Long id;
    private String name;
    private String number;
    private String arrivalCity;
    private String departureCity;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private List<Place> places = new LinkedList<>();

    @Override
    public TrainEntity toEntity() {
        return TrainEntity.builder()
                .name(name)
                .number(number)
                .arrivalCity(arrivalCity)
                .departureCity(departureCity)
                .arrivalTime(arrivalTime)
                .departureTime(departureTime)
                .places(places.stream()
                        .map(Place::toEntity)
                        .toList())
                .build();
    }
}

