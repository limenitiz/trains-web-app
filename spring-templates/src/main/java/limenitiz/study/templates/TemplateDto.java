package limenitiz.study.templates;

import java.util.List;

public interface TemplateDto <Entity extends TemplateEntity<?>> {

    default Entity toEntity(Long id) {
        var entity = toEntity();
        entity.setId(id);
        return entity;
    }
    Entity toEntity();
    void setId(Long id);

    /**
     * if dto not null: convert dto to entity
     * else: return null
     */
    default <InnerEntity extends TemplateEntity<?>>
    InnerEntity toEntity(TemplateDto<InnerEntity> dto) {
        InnerEntity entity = null;
        if (dto != null) {
            entity = dto.toEntity();
        }
        return entity;
    }
}
