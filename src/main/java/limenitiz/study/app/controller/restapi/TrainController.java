package limenitiz.study.app.controller.restapi;

import limenitiz.study.templates.CrudController;
import limenitiz.study.app.entity.TrainEntity;
import limenitiz.study.app.model.Train;
import limenitiz.study.app.service.TrainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/train")
public class TrainController
        extends CrudController<TrainService, Train, TrainEntity> {
    public TrainController(TrainService service) {
        super(service);
    }
}
