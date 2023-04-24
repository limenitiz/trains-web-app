package limenitiz.study.app.repository;

import limenitiz.study.templates.CrudRepository;
import limenitiz.study.app.entity.TrainEntity;

public interface TrainRepository
        extends CrudRepository<TrainEntity> {
    TrainEntity findByNumber(String number);
}
