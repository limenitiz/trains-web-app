package limenitiz.study.app.controller.restapi;

import limenitiz.study.app.entity.TrainExpressEntity;
import limenitiz.study.app.model.TrainExpress;
import limenitiz.study.app.service.TrainExpressService;
import limenitiz.study.templates.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/train-express")
public class TrainExpressController
        extends CrudController<TrainExpressService, TrainExpress, TrainExpressEntity> {
    public TrainExpressController(TrainExpressService service) {
        super(service);
    }
}
