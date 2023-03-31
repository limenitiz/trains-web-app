package limenitiz.study.restapi.entity;

import limenitiz.study.restapi.model.TrainExpress;
import limenitiz.study.templates.InvalidOperationException;
import limenitiz.study.templates.TemplateEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "train_express")
public class TrainExpressEntity
        implements TemplateEntity<TrainExpress> {

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

    @Override
    public <ParentEntity extends TemplateEntity<?>>
    void insertParent(ParentEntity parentEntity)
            throws InvalidOperationException {
        throw new InvalidOperationException("");
    }
}
