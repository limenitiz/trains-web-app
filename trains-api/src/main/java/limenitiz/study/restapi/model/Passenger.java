package limenitiz.study.restapi.model;

import limenitiz.study.templates.TemplateDto;
import limenitiz.study.restapi.entity.PassengerEntity;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger implements TemplateDto<PassengerEntity> {
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

