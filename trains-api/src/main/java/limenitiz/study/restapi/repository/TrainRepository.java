package limenitiz.study.restapi.repository;

import limenitiz.study.templates.CrudRepository;
import limenitiz.study.restapi.entity.TrainEntity;

public interface TrainRepository
        extends CrudRepository<TrainEntity> {
    TrainEntity findByName(String number);
}
