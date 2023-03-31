package limenitiz.study.restapi.controller;

import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.service.PlaceService;
import limenitiz.study.restapi.service.TrainExpressService;
import limenitiz.study.restapi.service.TrainService;
import limenitiz.study.templates.child.TemplateChildController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
public class PlaceController
        extends TemplateChildController<PlaceService, Place, PlaceEntity> {

    private final TrainService trainService;
    private final TrainExpressService trainExpressService;

    public PlaceController(PlaceService service,
                           TrainService trainService,
                           TrainExpressService trainExpressService) {
        super(service);
        this.trainService = trainService;
        this.trainExpressService = trainExpressService;
    }

    @PostMapping("/insert/train/{id}")
    public ResponseEntity<?> insertTrain(@PathVariable Long id,
                                         @RequestBody Place place) {
        return this.addElement(trainService, id, place);
    }

    @PostMapping("/insert/train-express/{id}")
    public ResponseEntity<?> insertTest(@PathVariable Long id,
                                         @RequestBody Place place) {
        return this.addElement(trainExpressService, id, place);
    }
}

