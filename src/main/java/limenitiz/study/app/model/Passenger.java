package limenitiz.study.app.model;

import limenitiz.study.templates.IDto;
import limenitiz.study.app.entity.PassengerEntity;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger implements IDto<PassengerEntity> {
    private Long id;
    private String firstName;
    private String secondName;
    private PassengerGender gender;

    @Override
    public PassengerEntity toEntity() {
        return PassengerEntity.builder()
                .firstName(firstName)
                .secondName(secondName)
                .gender(gender)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (!firstName.equals(passenger.firstName)) return false;
        if (!secondName.equals(passenger.secondName)) return false;
        return gender == passenger.gender;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }

    public static boolean isEquals(Passenger p1, Passenger p2) {
        return p1.equals(p2);
    }
}

