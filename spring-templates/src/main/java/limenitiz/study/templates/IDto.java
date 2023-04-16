package limenitiz.study.templates;

public interface IDto<Entity extends IEntity<?>> {

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
    default <InnerEntity extends IEntity<?>>
    InnerEntity toEntity(IDto<InnerEntity> dto) {
        InnerEntity entity = null;
        if (dto != null) {
            entity = dto.toEntity();
        }
        return entity;
    }
}
