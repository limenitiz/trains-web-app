package limenitiz.study.templates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository<Entity extends IEntity<?>>
        extends JpaRepository<Entity, Long> {
}
