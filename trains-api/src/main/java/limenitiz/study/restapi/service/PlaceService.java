package limenitiz.study.restapi.service;

import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.entity.TrainEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.model.Train;
import limenitiz.study.restapi.repository.PlaceRepository;
import limenitiz.study.restapi.repository.TrainRepository;
import limenitiz.study.templates.TemplateService;
import limenitiz.study.templates.child.TemplateChildController;
import limenitiz.study.templates.child.TemplateChildService;
import org.springframework.stereotype.Service;

@Service
public class PlaceService
        extends TemplateChildService<PlaceRepository, Place, PlaceEntity> {
    public PlaceService(PlaceRepository placeRepository) {
        super(placeRepository);
    }
}
