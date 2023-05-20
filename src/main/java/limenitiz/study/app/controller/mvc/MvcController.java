package limenitiz.study.app.controller.mvc;

import limenitiz.study.app.model.AbstractTrain;
import limenitiz.study.app.model.TrainExample;
import limenitiz.study.app.service.TrainExpressService;
import limenitiz.study.app.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class MvcController {
    private final TrainService trainService;
    private final TrainExpressService trainExpressService;

    public MvcController(TrainService trainService,
                         TrainExpressService trainExpressService) {
        this.trainService = trainService;
        this.trainExpressService = trainExpressService;
    }

    @GetMapping("/")
    public String root() {
        return home();
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAttribute("trainExample", new TrainExample());
        return "schedule";
    }

    @PostMapping("/schedule")
    public String searchTrains(TrainExample trainExample, Model model) {
        if (trainExample == null) {
            return "schedule";
        }
        var trains = trainService.findByDto(trainExample.toTrain(), TrainExample::compareTrains);
        var trainsExpress = trainExpressService.findByDto(trainExample.toTrainExpress(), TrainExample::compareTrains);
        var result = new ArrayList<AbstractTrain>();
        result.addAll(trains);
        result.addAll(trainsExpress);
        model.addAttribute("trains", result);
        return "schedule";
    }

}
