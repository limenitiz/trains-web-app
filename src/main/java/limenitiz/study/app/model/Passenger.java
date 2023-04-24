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
}

