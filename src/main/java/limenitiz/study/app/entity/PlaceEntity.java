package limenitiz.study.app.entity;


import limenitiz.study.templates.IEntity;
import limenitiz.study.app.model.Place;
import limenitiz.study.app.model.PlaceClass;
import javax.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
import java.util.Optional;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "place")
public class PlaceEntity
        implements IEntity<Place> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @Enumerated(EnumType.STRING)
    private PlaceClass placeClass;

    @Column(length = 10, scale = 2)
    private Double price;

    @ManyToOne
    private PassengerEntity passenger;

    @ManyToOne
    private TrainEntity train;

    @ManyToOne
    private TrainExpressEntity trainExpress;

    @Override
    public Place toDto() {
        return new Place(
                id,
                number,
                placeClass,
                toDto(passenger),
                Optional.ofNullable(price).orElse(0.0)
        );
    }
}
