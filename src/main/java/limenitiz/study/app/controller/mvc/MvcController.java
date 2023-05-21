package limenitiz.study.app.controller.mvc;

import limenitiz.study.app.model.AbstractTrain;
import limenitiz.study.app.model.Place;
import limenitiz.study.app.model.PlaceClass;
import limenitiz.study.app.model.TrainExample;
import limenitiz.study.app.service.PlaceService;
import limenitiz.study.app.service.TrainExpressService;
import limenitiz.study.app.service.TrainService;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @GetMapping("/trains")
    public String schedule(Model model) {
        model.addAttribute("trainExample", new TrainExample());
        return "trains";
    }

    @PostMapping("/trains")
    public String searchTrains(TrainExample trainExample, Model model) {
        if (trainExample == null) {
            return "trains";
        }
        var trains = trainService.findByDto(trainExample.toTrain(), TrainExample::compareTrains);
        var trainsExpress = trainExpressService.findByDto(trainExample.toTrainExpress(), TrainExample::compareTrains);
        var result = new ArrayList<AbstractTrain>();
        result.addAll(trains);
        result.addAll(trainsExpress);
        result.sort(Comparator.comparing(AbstractTrain::getNumber));
        model.addAttribute("trains", result);
        return "trains";
    }

    @GetMapping("/places/{placeClass}/{trainClass}/{trainID}")
    public String places(Model model,
                         @PathVariable Long trainID,
                         @PathVariable PlaceClass placeClass,
                         @PathVariable String trainClass) {
        AbstractTrain train = null;

        switch (trainClass) {
            case "Train" -> {
                try {
                    train = trainService.findById(trainID);
                } catch (NotFoundException ignored) { }
            }
            case "TrainExpress" -> {
                try {
                    train = trainExpressService.findById(trainID);
                } catch (NotFoundException ignored) { }
            }
        }

        if (train == null) {
            model.addAttribute("processing-error", "Train not found");
            return "places";
        }

        model.addAttribute("train", train);

        model.addAttribute("availablePlaces",
                train.places(placeClass).stream()
                        .filter(Place::isAvailable)
                        .sorted(Comparator.comparing(Place::getNumber))
                        .toList()
        );
        model.addAttribute("unavailablePlaces",
                train.places(placeClass).stream()
                        .filter(place -> !place.isAvailable())
                        .sorted(Comparator.comparing(Place::getNumber))
                        .toList()
        );
        return "places";
    }
}
