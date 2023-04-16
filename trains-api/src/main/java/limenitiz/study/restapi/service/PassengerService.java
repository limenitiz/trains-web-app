package limenitiz.study.restapi.service;

import limenitiz.study.restapi.entity.PassengerEntity;
import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.model.Passenger;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.repository.PassengerRepository;
import limenitiz.study.restapi.repository.PlaceRepository;
import limenitiz.study.templates.CrudService;
import org.springframework.stereotype.Service;

@Service
public class PassengerService
        extends CrudService<PassengerRepository, Passenger, PassengerEntity> {
    public PassengerService(PassengerRepository passengerRepository) {
        super(passengerRepository);
    }
}
