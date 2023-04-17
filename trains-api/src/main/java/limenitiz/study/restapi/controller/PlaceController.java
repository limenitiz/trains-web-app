package limenitiz.study.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import limenitiz.study.restapi.entity.PlaceEntity;
import limenitiz.study.restapi.model.Place;
import limenitiz.study.restapi.service.PlaceService;
import limenitiz.study.templates.CrudController;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
public class PlaceController
        extends CrudController<PlaceService, Place, PlaceEntity> {

    public PlaceController(PlaceService service) {
        super(service);
    }

    @PostMapping("/insert/train/{id}")
    public ResponseEntity<?> insertIntoTrain(@PathVariable Long id,
                                         @RequestBody Place place) throws NotFoundException {
        service.insertIntoTrain(place, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();

    }

    @PostMapping("/insert/train-express/{id}")
    public ResponseEntity<?> insertIntoTrainExpress(@PathVariable Long id,
                                                @RequestBody Place place) throws NotFoundException {
        service.insertIntoTrainExpress(place, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @Operation(summary = "Remove passenger from place by place id")
    @DeleteMapping("/{id}/passenger")
    public ResponseEntity<?> removeFromPlaceByPlaceID (@PathVariable Long id) throws NotFoundException {
        service.removePassengerFromPlaceByID(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @Override
    @Operation(hidden = true)
    public ResponseEntity<Void> addElement(Place place) {
        throw new UnsupportedOperationException("");
    }
}

