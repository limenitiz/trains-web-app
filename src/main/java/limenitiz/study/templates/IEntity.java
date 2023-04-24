package limenitiz.study.templates;


public interface IEntity<Dto extends IDto<?>> {
     Dto toDto();

     void setId(Long id);
     Long getId();

     /**
      * if entity not null: convert entity to dto
      * else: return null
      */
     default <InnerDto extends IDto<?>>
     InnerDto toDto(IEntity<InnerDto> entity) {
          InnerDto dto = null;
          if (entity != null) {
               dto = entity.toDto();
          }
          return dto;
     }
}
