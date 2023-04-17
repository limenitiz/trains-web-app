package limenitiz.study.restapi.entity;


import limenitiz.study.templates.IEntity;
import limenitiz.study.restapi.model.Train;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "train")
public class TrainEntity
        implements IEntity<Train> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String arrivalCity;
    private String departureCity;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "train")
    private final List<PlaceEntity> places = new LinkedList<>();

    @Override
    public Train toDto() {
        return new Train(
                id,
                name,
                number,
                arrivalCity,
                departureCity,
                arrivalTime,
                departureTime,
                new LinkedList<>(
                        places.stream()
                                .map(PlaceEntity::toDto)
                                .toList())
        );
    }
}

