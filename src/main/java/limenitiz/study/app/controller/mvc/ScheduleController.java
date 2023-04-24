package limenitiz.study.app.controller.mvc;

import limenitiz.study.app.service.TrainExpressService;
import limenitiz.study.app.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ScheduleController {
    private final TrainService trainService;
    private final TrainExpressService trainExpressService;

    public ScheduleController(TrainService trainService,
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
        model.addAttribute("trains", trainService.getAll());
        model.addAttribute("trainsExpress", trainExpressService.getAll());
        return "schedule";
    }

}
