package limenitiz.study.templates.child;

import io.swagger.v3.oas.annotations.Operation;
import limenitiz.study.templates.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public abstract class TemplateChildController
        <Service extends TemplateChildService<?, Dto, Entity>,
                Dto extends TemplateDto<Entity>,
                Entity extends TemplateEntity<Dto>
                > extends TemplateController<Service, Dto, Entity> {

    public TemplateChildController(Service service) {
        super(service);
    }


    public <ParentService extends TemplateService<ParentRepository, ParentDto, ParentEntity>,
            ParentRepository extends TemplateRepository<ParentEntity>,
            ParentDto extends TemplateDto<ParentEntity>,
            ParentEntity extends TemplateEntity<ParentDto>>
    ResponseEntity<Void> addElement(ParentService parentService, Long parentID, Dto childElement) {
        try {
            service.insertById(parentService, parentID, childElement);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .build();

        } catch (InvalidOperationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .build();

        } catch (NotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @Override
    @Operation(hidden = true)
    public ResponseEntity<Void> addElement(Dto dto) {
        throw new UnsupportedOperationException("");
    }

}

