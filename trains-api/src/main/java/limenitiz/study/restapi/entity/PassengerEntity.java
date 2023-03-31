package limenitiz.study.restapi.entity;

import limenitiz.study.restapi.model.Passenger;
import limenitiz.study.restapi.model.PassengerGender;
import limenitiz.study.templates.InvalidOperationException;
import lombok.*;
import limenitiz.study.templates.TemplateEntity;

import javax.persistence.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "passenger")
public class PassengerEntity
        implements TemplateEntity<Passenger> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    @Enumerated(EnumType.STRING)
    private PassengerGender gender;

    @OneToOne
    private PlaceEntity place;

    @Override
    public Passenger toDto() {
        return new Passenger(
                this.id,
                this.firstName,
                this.secondName,
                this.gender
        );
    }

    @Override
    public <ParentEntity extends TemplateEntity<?>>
    void insertParent(ParentEntity parentEntity)
            throws InvalidOperationException {
        if (parentEntity instanceof PlaceEntity) {
            place = (PlaceEntity) parentEntity;
        } else {
            throw new InvalidOperationException("Error: Unknown type. " +
                    "Impossible insert object into Passenger.");
        }
    }
}

