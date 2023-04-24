package limenitiz.study.app.service;

import limenitiz.study.app.entity.TrainExpressEntity;
import limenitiz.study.app.model.TrainExpress;
import limenitiz.study.app.repository.TrainExpressRepository;
import limenitiz.study.templates.CrudService;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TrainExpressService
        extends CrudService<TrainExpressRepository, TrainExpress, TrainExpressEntity> {
    public TrainExpressService(TrainExpressRepository repository) {
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
