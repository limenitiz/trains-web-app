package limenitiz.study.restapi.entity;


import limenitiz.study.restapi.model.Passenger;
import limenitiz.study.restapi.model.PassengerGender;
import lombok.*;
import limenitiz.study.templates.IEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "passenger")
public class PassengerEntity
        implements IEntity<Passenger> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    @Enumerated(EnumType.STRING)
    private PassengerGender gender;

    @OneToMany(mappedBy = "passenger")
    private final List<PlaceEntity> places = new LinkedList<>();

    @Override
    public Passenger toDto() {
        return new Passenger(
                this.id,
                this.firstName,
                this.secondName,
                this.gender
        );
    }
}

