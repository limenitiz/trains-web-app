package limenitiz.study.restapi.repository;

import limenitiz.study.templates.TemplateRepository;
import limenitiz.study.restapi.entity.TrainEntity;

public interface TrainRepository
        extends TemplateRepository<TrainEntity> {
    TrainEntity findByName(String number);
}
