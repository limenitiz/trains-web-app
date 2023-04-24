package limenitiz.study.app.controller.restapi;

import io.swagger.v3.oas.annotations.Operation;
import limenitiz.study.app.entity.PassengerEntity;
import limenitiz.study.app.model.Passenger;
import limenitiz.study.app.service.PassengerService;
import limenitiz.study.templates.CrudController;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController
        extends CrudController<PassengerService, Passenger, PassengerEntity> {

    public PassengerController(PassengerService service) {
        super(service);
    }

    @Operation(summary = "Insert a new element and link with the parent")
    @PostMapping("/insert/place/{id}")
    public ResponseEntity<?> insertPlace(@PathVariable Long id,
                                         @RequestBody Passenger passenger) throws NotFoundException {
        service.insertIntoPlace(passenger, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @Operation(summary = "Link existing element with the parent")
    @PostMapping("/{passengerID}/place/{placeID}")
    public ResponseEntity<?> linkWithPlace(@PathVariable Long passengerID,
                                           @PathVariable Long placeID) throws NotFoundException {
        service.linkWithPlace(passengerID, placeID);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}

