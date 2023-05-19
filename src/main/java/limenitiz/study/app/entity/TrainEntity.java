package limenitiz.study.app.entity;


import limenitiz.study.app.model.Place;
import limenitiz.study.templates.IEntity;
import limenitiz.study.app.model.Train;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    private String number;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "train")
    private final List<PlaceEntity> places = new ArrayList<>();

    @Override
    public Train toDto() {
        return new Train(
                id,
                number,
                arrivalCity,
                departureCity,
                arrivalTime,
                departureTime,
                new ArrayList<>(
                        places.stream()
                                .map(PlaceEntity::toDto)
                                .sorted(Comparator.comparingInt(Place::getNumber))
                                .toList())
        );
    }
}

