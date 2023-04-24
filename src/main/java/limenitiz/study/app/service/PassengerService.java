package limenitiz.study.app.service;

import limenitiz.study.app.entity.PassengerEntity;
import limenitiz.study.app.model.Passenger;
import limenitiz.study.app.repository.PassengerRepository;
import limenitiz.study.app.repository.PlaceRepository;
import limenitiz.study.templates.CrudService;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PassengerService
        extends CrudService<PassengerRepository, Passenger, PassengerEntity> {
    public PassengerService(PassengerRepository passengerRepository,
                            PlaceRepository placeRepository) {
        super(passengerRepository);
        this.placeRepository = placeRepository;
    }

    private final PlaceRepository placeRepository;

    @Transactional
    public void insertIntoPlace(Passenger passenger, Long placeId) throws NotFoundException {
        var placeEntity = placeRepository
                .findById(placeId)
                .orElseThrow(() -> new NotFoundException("Train not found"));
        var passengerEntity = passenger.toEntity();

        passengerEntity.getPlaces().add(placeEntity);
        placeEntity.setPassenger(passengerEntity);

        repository.save(passengerEntity);
        placeRepository.save(placeEntity);
    }

    @Transactional
    public void linkWithPlace(Long passengerID, Long placeID) throws NotFoundException {
        var passengerEntity = repository
                .findById(passengerID)
                .orElseThrow(() -> new NotFoundException("Passenger not found"));

        var placeEntity = placeRepository
                .findById(placeID)
                .orElseThrow(() -> new NotFoundException("Place not found"));

        passengerEntity.getPlaces().add(placeEntity);
        placeEntity.setPassenger(passengerEntity);

        repository.save(passengerEntity);
        placeRepository.save(placeEntity);
    }

    @Override
    public void removeById(Long id) throws NotFoundException {
        var passengerEntity = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Passenger not found"));
        passengerEntity.getPlaces().forEach(e -> {
            e.setPassenger(null);
            placeRepository.save(e);
        });
        repository.deleteById(id);
    }
}
