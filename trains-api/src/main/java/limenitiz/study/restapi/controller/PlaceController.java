package limenitiz.study.restapi.controller;

import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.service.PlaceService;
import limenitiz.study.restapi.service.TrainExpressService;
import limenitiz.study.restapi.service.TrainService;
import limenitiz.study.templates.CrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
public class PlaceController
        extends CrudController<PlaceService, Place, PlaceEntity> {

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
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @PostMapping("/insert/train-express/{id}")
    public ResponseEntity<?> insertTest(@PathVariable Long id,
                                         @RequestBody Place place) {
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}

