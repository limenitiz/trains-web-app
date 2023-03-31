package limenitiz.study.templates;


import java.util.List;

public interface TemplateEntity <Dto extends TemplateDto<?>> {
     Dto toDto();

     void setId(Long id);
     Long getId();

     <ParentEntity extends TemplateEntity<?>>
     void insertParent(ParentEntity parentEntity)
             throws InvalidOperationException;

     /**
      * if entity not null: convert entity to dto
      * else: return null
      */
     default <InnerDto extends TemplateDto<?>>
     InnerDto toDto(TemplateEntity<InnerDto> entity) {
          InnerDto dto = null;
          if (entity != null) {
               dto = entity.toDto();
          }
          return dto;
     }
}
