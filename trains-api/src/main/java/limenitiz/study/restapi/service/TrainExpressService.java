package limenitiz.study.restapi.service;

import limenitiz.study.restapi.entity.TrainExpressEntity;
import limenitiz.study.restapi.model.TrainExpress;
import limenitiz.study.restapi.repository.TrainExpressRepository;
import limenitiz.study.templates.TemplateService;
import org.springframework.stereotype.Service;

@Service
public class TrainExpressService
        extends TemplateService<TrainExpressRepository, TrainExpress, TrainExpressEntity> {
    public TrainExpressService(TrainExpressRepository repository) {
        super(repository);
    }
}
