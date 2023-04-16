package limenitiz.study.restapi.service;

import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.repository.PlaceRepository;
import limenitiz.study.templates.CrudService;
import org.springframework.stereotype.Service;

@Service
public class PlaceService
        extends CrudService<PlaceRepository, Place, PlaceEntity> {
    public PlaceService(PlaceRepository placeRepository) {
        super(placeRepository);
    }
}
