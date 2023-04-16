package limenitiz.study.restapi.service;

import limenitiz.study.templates.CrudService;
import limenitiz.study.restapi.entity.TrainEntity;
import limenitiz.study.restapi.model.Train;
import limenitiz.study.restapi.repository.TrainRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainService
        extends CrudService<TrainRepository, Train, TrainEntity> {
    public TrainService(TrainRepository repository) {
        super(repository);
    }
}
