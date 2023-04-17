package limenitiz.study.templates;

import limenitiz.study.templates.exception.NotFoundException;
import lombok.Getter;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;

import javax.transaction.Transactional;
import java.util.List;

public abstract class CrudService
        <Repository extends CrudRepository<Entity>,
                Dto extends IDto<Entity>,
                Entity extends IEntity<Dto>> {
    @Getter protected Repository repository;

    public CrudService(Repository repository) {
        this.repository = repository;
    }

    @Transactional
    public void add(Dto dto) {
        repository.save(dto.toEntity());
    }

    @Transactional
    public void removeById(Long id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Transactional
    public void updateById(Long id, Dto dto) throws NotFoundException {
        Entity entity = dto.toEntity(id);
        repository.save(entity);
    }

    public Dto findById(Long id) throws NotFoundException {
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
                .map(IEntity::toDto)
                .toList();
    }
}

