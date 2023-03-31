package limenitiz.study.restapi.model;

import limenitiz.study.restapi.entity.TrainExpressEntity;
import limenitiz.study.templates.TemplateDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainExpress
        implements TemplateDto<TrainExpressEntity> {
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
        return TrainExpressEntity.builder()
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
