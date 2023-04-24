package limenitiz.study.app.service;

import limenitiz.study.templates.CrudService;
import limenitiz.study.app.entity.TrainEntity;
import limenitiz.study.app.model.Train;
import limenitiz.study.app.repository.TrainRepository;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TrainService
        extends CrudService<TrainRepository, Train, TrainEntity> {
    public TrainService(TrainRepository repository) {
        super(repository);
    }

    @Override
    public void removeById(Long id) throws NotFoundException {
        var trainExpressEntity = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Train express not found"));
        trainExpressEntity.getPlaces().forEach(e -> e.setTrainExpress(null));
        repository.save(trainExpressEntity);
        repository.delete(trainExpressEntity);
    }
}
