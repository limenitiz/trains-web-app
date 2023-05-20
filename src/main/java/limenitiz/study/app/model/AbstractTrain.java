package limenitiz.study.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SuperBuilder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractTrain {
    protected Long id;
    protected String number;
    protected String arrivalCity;
    protected String departureCity;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    protected LocalDateTime arrivalTime;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    protected LocalDateTime departureTime;

    protected List<Place> places = new LinkedList<>();
    public int availablePlacesCount(PlaceClass placeClass) {
        return places.stream()
                .filter(p -> {
                    if (placeClass == null) { return true; }
                    return p.getPlaceClass().equals(placeClass);
                })
                .filter(place -> place.getPassenger() == null)
                .toList()
                .size();
    }

    public int placesCount(PlaceClass placeClass) {
        return places.stream()
                .filter(p -> {
                    if (placeClass == null) { return true; }
                    return p.getPlaceClass().equals(placeClass);
                })
                .toList()
                .size();
    }

    public String minPlacePrice(PlaceClass placeClass) {
        return String.format("%,.2f Р", places.stream()
                .filter(p -> {
                    if (placeClass == null) { return true; }
                    return p.getPlaceClass().equals(placeClass);
                })
                .min(Comparator.comparingDouble(Place::getPrice))
                .map(Place::getPrice)
                .orElse(0.0));
    }

    public String maxPlacePrice(PlaceClass placeClass) {
        return String.format("%,.2f Р", places.stream()
                .filter(p -> {
                    if (placeClass == null) { return true; }
                    return p.getPlaceClass().equals(placeClass);
                })
                .max(Comparator.comparingDouble(Place::getPrice))
                .map(Place::getPrice)
                .orElse(0.0));
    }

    public String getArrivalTime() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(
                Optional.ofNullable(arrivalTime)
                        .orElse(LocalDateTime.now()));
    }

    public String getDepartureTime() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(
                Optional.ofNullable(departureTime)
                        .orElse(LocalDateTime.now()));
    }
}

