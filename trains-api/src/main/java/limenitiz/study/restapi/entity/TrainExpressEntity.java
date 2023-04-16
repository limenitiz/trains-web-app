package limenitiz.study.restapi.entity;

import limenitiz.study.restapi.model.TrainExpress;
import limenitiz.study.templates.IEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "train_express")
public class TrainExpressEntity
        implements IEntity<TrainExpress> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String arrivalCity;
    private String departureCity;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "trainExpress")
    private Collection<PlaceEntity> places;

    @Override
    public TrainExpress toDto() {
        return new TrainExpress(
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
