package limenitiz.study.templates.child;

import limenitiz.study.templates.*;

import javax.transaction.Transactional;


public abstract class TemplateChildService
        <Repository extends TemplateRepository<Entity>,
                Dto extends TemplateDto<Entity>,
                Entity extends TemplateEntity<Dto>
                > extends TemplateService<Repository, Dto, Entity> {

    public TemplateChildService(Repository repository) {
        super(repository);
    }


    @Transactional
    public <ParentService extends TemplateService<ParentRepository, ParentDto, ParentEntity>,
            ParentRepository extends TemplateRepository<ParentEntity>,
            ParentEntity extends TemplateEntity<ParentDto>,
            ParentDto extends TemplateDto<ParentEntity>>
    void insertById(ParentService parentService, Long parentID, Dto dto)
            throws InvalidOperationException, NotFoundException {
        ParentRepository parentRepository = parentService.getRepository();

        ParentEntity parentEntity = parentRepository.findById(parentID)
                .orElseThrow(() -> new NotFoundException(""));

        Entity entity = dto.toEntity();
        entity.insertParent(parentEntity);
        repository.save(entity);
    }
}
