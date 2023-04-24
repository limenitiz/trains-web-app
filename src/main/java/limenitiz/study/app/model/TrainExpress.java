package limenitiz.study.app.model;

import limenitiz.study.app.entity.TrainExpressEntity;
import limenitiz.study.templates.IDto;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainExpress
        implements IDto<TrainExpressEntity> {
    private Long id;
    private String number;
    private String arrivalCity;
    private String departureCity;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private List<Place> places = new LinkedList<>();

    public int availablePlacesCount() {
        return places.stream()
                .filter(place -> place.getPassenger() == null)
                .toList()
                .size();
    }

    public int placesCount() {
        return places.size();
    }

    public String minPlacePrice() {
        return String.format("%,.2f Р", places.stream()
                .min(Comparator.comparingDouble(Place::getPrice))
                .map(Place::getPrice)
                .orElse(0.0));
    }

    public String maxPlacePrice() {
        return String.format("%,.2f Р", places.stream()
                .max(Comparator.comparingDouble(Place::getPrice))
                .map(Place::getPrice)
                .orElse(0.0));
    }


    public String getArrivalTime() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(arrivalTime);
    }

    public String getDepartureTime() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(departureTime);
    }

    @Override
    public TrainExpressEntity toEntity() {
        var e = new TrainExpressEntity(
                null,
                number,
                arrivalCity,
                departureCity,
                arrivalTime,
                departureTime
        );

        e.getPlaces().addAll(places.stream()
                .map(Place::toEntity)
                .toList()
        );

        return e;
    }
}
