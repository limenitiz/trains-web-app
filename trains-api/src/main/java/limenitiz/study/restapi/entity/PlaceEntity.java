package limenitiz.study.restapi.entity;


import limenitiz.study.templates.InvalidOperationException;
import limenitiz.study.templates.TemplateEntity;
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
        implements TemplateEntity<Place> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @Enumerated(EnumType.STRING)
    private PlaceClass placeClass;

    @OneToOne(mappedBy = "place")
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

    @Override
    public <ParentEntity extends TemplateEntity<?>>
    void insertParent(ParentEntity parentEntity)
            throws InvalidOperationException {
        if (parentEntity instanceof TrainEntity) {
            train = (TrainEntity) parentEntity;
        } else if (parentEntity instanceof TrainExpressEntity) {
            trainExpress = (TrainExpressEntity) parentEntity;
        }
        else {
            throw new InvalidOperationException("Error: Unknown type. " +
                    "Impossible insert object into Place.");
        }
    }
}

