package limenitiz.study.templates;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public abstract class TemplateController
        <Service extends TemplateService<?, Dto, Entity>,
                Dto extends TemplateDto<Entity>,
                Entity extends TemplateEntity<Dto>> {

    protected final Service service;

    public TemplateController(Service service) {
        this.service = service;
    }

    @PostMapping("/add")
    @Operation(summary = "Add element")
    public ResponseEntity<Void> addElement(@RequestBody Dto dto) {
        service.add(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @GetMapping("/get/all")
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

    @GetMapping("/get/{id}")
    @Operation(summary = "Get element by id")
    public ResponseEntity<?> getElementById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.getById(id));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{ \"error\": \"" + e.getMessage() + "\" }"
                    );
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Edit element by id")
    public ResponseEntity<Void> updateElement(
            @PathVariable Long id,
            @RequestBody Dto dto) {

        service.updateById(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete element by id")
    public ResponseEntity<Void> deleteElementById(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}
