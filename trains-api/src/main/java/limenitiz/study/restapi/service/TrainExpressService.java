package limenitiz.study.restapi.service;

import limenitiz.study.restapi.entity.TrainExpressEntity;
import limenitiz.study.restapi.model.TrainExpress;
import limenitiz.study.restapi.repository.TrainExpressRepository;
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
