package limenitiz.study.templates;

import lombok.Getter;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;

import javax.transaction.Transactional;
import java.util.List;

public abstract class TemplateService
        <Repository extends TemplateRepository<Entity>,
                Dto extends TemplateDto<Entity>,
                Entity extends TemplateEntity<Dto>> {
    @Getter protected Repository repository;

    public TemplateService(Repository repository) {
        this.repository = repository;
    }

    @Transactional
    public void add(Dto dto) {
        repository.save(dto.toEntity());
    }

    @Transactional
    public void removeById(Long id) {
//        repository.deleteById(id);
        var entity = repository.findById(id);
        entity.ifPresent((e) -> repository.delete(e));
    }

    @Transactional
    public void updateById(Long id, Dto dto) {
        Entity entity = dto.toEntity(id);
        repository.save(entity);
    }

    public Dto getById(Long id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Element not found"))
                .toDto();
    }

    public List<Dto> getAll() {
        return repository.findAll().stream()
                .map(Entity::toDto)
                .toList();
    }

    public List<Dto> findByDto(Dto dto) {
        List<Entity> entities = repository.findBy(Example.of(dto.toEntity()),
                FluentQuery.FetchableFluentQuery::all);

        return entities.stream()
                .map(TemplateEntity::toDto)
                .toList();
    }
}

