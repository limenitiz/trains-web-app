package limenitiz.study.restapi.controller;

import limenitiz.study.restapi.entity.PassengerEntity;
import limenitiz.study.restapi.model.Passenger;
import limenitiz.study.restapi.service.PassengerService;
import limenitiz.study.restapi.service.PlaceService;
import limenitiz.study.templates.CrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController
        extends CrudController<PassengerService, Passenger, PassengerEntity> {

    private final PlaceService placeService;

    public PassengerController(PassengerService service, PlaceService placeService) {
        super(service);
        this.placeService = placeService;
    }

    @PostMapping("/insert/place/{id}")
    public ResponseEntity<?> insertPlace(@PathVariable Long id,
                                         @RequestBody Passenger passenger) {
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
