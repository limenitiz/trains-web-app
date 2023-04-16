package limenitiz.study.restapi.controller;

import limenitiz.study.restapi.entity.TrainExpressEntity;
import limenitiz.study.restapi.model.TrainExpress;
import limenitiz.study.restapi.service.TrainExpressService;
import limenitiz.study.templates.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/train-express")
public class TrainExpressController
        extends CrudController<TrainExpressService, TrainExpress, TrainExpressEntity> {
    public TrainExpressController(TrainExpressService service) {
        super(service);
    }
}
