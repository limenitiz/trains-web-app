package limenitiz.study.app.controller.mvc;

import limenitiz.study.app.model.*;
import limenitiz.study.app.service.PassengerService;
import limenitiz.study.app.service.PlaceService;
import limenitiz.study.app.service.TrainExpressService;
import limenitiz.study.app.service.TrainService;
import limenitiz.study.templates.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class MvcController {
    private final TrainService trainService;
    private final TrainExpressService trainExpressService;
    private final PassengerService passengerService;
    private final PlaceService placeService;

    public MvcController(TrainService trainService,
                         TrainExpressService trainExpressService,
                         PassengerService passengerService,
                         PlaceService placeService) {
        this.trainService = trainService;
        this.trainExpressService = trainExpressService;
        this.passengerService = passengerService;
        this.placeService = placeService;
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

    @GetMapping("/places/{place-class}/{train-class}/{train-id}")
    public String places(Model model,
                         @PathVariable(required=true,  name = "train-id") Long trainId,
                         @PathVariable(required=true,  name = "place-class") PlaceClass placeClass,
                         @PathVariable(required=true,  name = "train-class") String trainClass,
                         @RequestParam(required=false, name = "sort-type") String sortType) {
        AbstractTrain train = null;

        switch (trainClass) {
            case "Train" -> {
                try {
                    train = trainService.findById(trainId);
                } catch (NotFoundException ignored) { }
            }
            case "TrainExpress" -> {
                try {
                    train = trainExpressService.findById(trainId);
                } catch (NotFoundException ignored) { }
            }
        }

        if (train == null) {
            model.addAttribute("processing-error", "Train not found");
            return "places";
        }

        model.addAttribute("train", train);
        model.addAttribute("passenger", new Passenger());
        model.addAttribute("placeClass", placeClass);


        if ("price".equals(sortType)) {
            model.addAttribute("availablePlaces",
                    train.places(placeClass).stream()
                            .filter(Place::isAvailable)
                            .sorted(Comparator.comparing(Place::getPrice))
                            .toList()
            );
            model.addAttribute("unavailablePlaces",
                    train.places(placeClass).stream()
                            .filter(place -> !place.isAvailable())
                            .sorted(Comparator.comparing(Place::getPrice))
                            .toList()
            );
        }
        else if ("number".equals(sortType)) {
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
        }
        else {
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
        }


        return "places";
    }

    @PostMapping("/places/{place-id}/{place-class}/{train-class}/{train-id}")
    public String buyPlace(Model model,
                           Passenger passenger,
                           @PathVariable(required=true,  name = "train-id") Long trainId,
                           @PathVariable(required=true,  name = "place-class") PlaceClass placeClass,
                           @PathVariable(required=true,  name = "train-class") String trainClass,
                           @RequestParam(required=false, name = "sort-type") String sortType,
                           @PathVariable(name = "place-id") Long placeId) {

        try {
            var place = placeService.findById(placeId);
            if (place.getPassenger() != null) {
                throw new Exception("Place is taken");
            }

            List<Passenger> existPassengerList = passengerService.findByDto(passenger, Passenger::isEquals);
            Passenger existPassenger = null;
            if (existPassengerList.size() > 0) {
                existPassenger = existPassengerList.get(0);
            }
            if (existPassenger != null) {
                passengerService.linkWithPlace(existPassenger.getId(), placeId);
            } else {
                passengerService.insertIntoPlace(passenger, placeId);
            }

            model.addAttribute("buyPlaceStatus", "success");
        } catch (Exception e) {
            model.addAttribute("buyPlaceStatus", "error");
            model.addAttribute("buyPlaceError", e.getMessage());
        }
        return places(model, trainId, placeClass, trainClass, sortType);
    }
}
