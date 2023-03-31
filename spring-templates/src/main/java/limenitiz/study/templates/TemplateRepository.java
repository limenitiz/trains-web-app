package limenitiz.study.templates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository <Entity extends TemplateEntity<?>>
        extends JpaRepository<Entity, Long> {
}
