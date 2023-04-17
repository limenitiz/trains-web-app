package limenitiz.study.restapi.service;

import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.repository.PlaceRepository;
import limenitiz.study.restapi.repository.TrainExpressRepository;
import limenitiz.study.restapi.repository.TrainRepository;
import limenitiz.study.templates.CrudService;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlaceService
        extends CrudService<PlaceRepository, Place, PlaceEntity> {
    public PlaceService(PlaceRepository placeRepository,
                        TrainRepository trainRepository,
                        TrainExpressRepository trainExpressRepository) {
        super(placeRepository);
        this.trainRepository = trainRepository;
        this.trainExpressRepository = trainExpressRepository;
    }

    private final TrainRepository trainRepository;
    private final TrainExpressRepository trainExpressRepository;

    @Transactional
    public void insertIntoTrain(Place place, Long trainId) throws NotFoundException {
        var parentEntity = trainRepository
                .findById(trainId)
                .orElseThrow(() -> new NotFoundException("Train not found"));
        var childEntity = place.toEntity();
        childEntity.setTrain(parentEntity);
        repository.save(childEntity);
    }

    @Transactional
    public void insertIntoTrainExpress(Place place, Long trainExpressId) throws NotFoundException  {
        var trainExpressEntity = trainExpressRepository
                .findById(trainExpressId)
                .orElseThrow(() -> new NotFoundException("Train not found"));
        var placeEntity = place.toEntity();
        placeEntity.setTrainExpress(trainExpressEntity);
        repository.save(placeEntity);
    }

    @Override
    public void removeById(Long id) throws NotFoundException {
        var placeEntity = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Place not found"));

        placeEntity.setPassenger(null);
        placeEntity.setTrain(null);
        placeEntity.setTrainExpress(null);
        repository.save(placeEntity);
        repository.delete(placeEntity);
    }

    @Transactional
    public void removePassengerFromPlaceByID(Long placeID) throws NotFoundException {
        var placeEntity = repository
                .findById(placeID)
                .orElseThrow(() -> new NotFoundException("Place not found"));
        placeEntity.setPassenger(null);
        repository.save(placeEntity);
    }

}

