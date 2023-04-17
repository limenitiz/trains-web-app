package limenitiz.study.restapi.entity;


import limenitiz.study.templates.IEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.model.PlaceClass;
import javax.persistence.*;
import lombok.*;


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
                toDto(passenger)
        );
    }
}
