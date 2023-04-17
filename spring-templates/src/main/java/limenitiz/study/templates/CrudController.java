package limenitiz.study.templates;

import io.swagger.v3.oas.annotations.Operation;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public abstract class CrudController
        <Service extends CrudService<?, Dto, Entity>,
                Dto extends IDto<Entity>,
                Entity extends IEntity<Dto>> {

    protected final Service service;

    public CrudController(Service service) {
        this.service = service;
    }

    @PostMapping("/")
    @Operation(summary = "Add element")
    public ResponseEntity<Void> addElement(@RequestBody Dto dto) {
        service.add(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @GetMapping("/")
    @Operation(summary = "Get a list of all elements")
    public ResponseEntity<List<Dto>> getAllElements() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll());
    }

    @PostMapping("/search")
    @Operation(summary = "Search element by example")
    public ResponseEntity<List<Dto>> findElement(@RequestBody Dto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByDto(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get element by id")
    public ResponseEntity<?> getElementById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(id));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit element by id")
    public ResponseEntity<Void> updateElement(@PathVariable Long id,
                                              @RequestBody Dto dto) throws NotFoundException {
        service.updateById(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete element by id")
    public ResponseEntity<Void> deleteElementById(@PathVariable Long id) throws NotFoundException {
        service.removeById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}
