package limenitiz.study.restapi.controller;

import limenitiz.study.templates.TemplateController;
import limenitiz.study.restapi.entity.TrainEntity;
import limenitiz.study.restapi.model.Train;
import limenitiz.study.restapi.service.TrainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/train")
public class TrainController
        extends TemplateController<TrainService, Train, TrainEntity> {
    public TrainController(TrainService service) {
        super(service);
    }
}
