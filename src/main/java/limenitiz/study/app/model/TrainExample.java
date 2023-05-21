package limenitiz.study.app.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter
public class TrainExample extends AbstractTrain {
    private String arrivalTime;
    private String departureTime;

    public TrainExample() {
    }

    public TrainExample(Long id, String number,
                        String arrivalCity, String departureCity,
                        String arrivalTime, String departureTime,
                        List<Place> places) {
        super(id, number,
                arrivalCity, departureCity,
                parseDateTime(arrivalTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                parseDateTime(departureTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                places);
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
    }

    public Train toTrain() {
        clearFields();
        return new Train(
            this.id, this.number,
                this.arrivalCity, this.departureCity,
                parseDateTime(this.arrivalTime),
                parseDateTime(this.departureTime),
                this.places
        );
    }

    public TrainExpress toTrainExpress() {
        clearFields();
        return new TrainExpress(
                this.id, this.number,
                this.arrivalCity, this.departureCity,
                parseDateTime(this.arrivalTime),
                parseDateTime(this.departureTime),
                this.places
        );
    }

    private void clearFields() {
        this.arrivalCity = clearField(arrivalCity);
        this.departureCity = clearField(departureCity);
        this.arrivalTime = clearField(arrivalTime);
        this.departureTime = clearField(departureTime);
    }

    private static String clearField(String s) {
        if ("".equals(s)) {
            return null;
        }
        return s;
    }

    private static LocalDateTime parseDateTime(String s) {
        if (s == null) {
            return null;
        }
        if ("".equals(s)) {
            return null;
        }
        return LocalDateTime.parse(s);
    }

    private static LocalDateTime parseDateTime(String s, DateTimeFormatter formatter) {
        if (s == null) {
            return null;
        }
        if ("".equals(s)) {
            return null;
        }
        return LocalDateTime.parse(s, formatter);
    }

    public static  <T extends AbstractTrain> Boolean compareTrains(T o1, T o2) {
        // o1 - entity in db
        // o2 - dto
        boolean departureCity = true;
        boolean arrivalCity = true;
        boolean departureTime = true;
        boolean arrivalTime = true;

        if (o1.getDepartureCity() != null && o2.getDepartureCity() != null) {
            departureCity = o1.getDepartureCity().equals(o2.getDepartureCity());
        }
        if (o1.getArrivalCity() != null && o2.getArrivalCity() != null) {
            arrivalCity = o1.getArrivalCity().equals(o2.getArrivalCity());
        }
        if (o1.getDepartureTime() != null && o2.getDepartureTime() != null) {
            departureTime = LocalDateTime.parse(o2.getDepartureTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).isBefore(
                    LocalDateTime.parse(o1.getDepartureTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            );
        }
        if (o1.getArrivalTime() != null && o2.getArrivalTime() != null) {
            arrivalTime = LocalDateTime.parse(o2.getArrivalTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).isAfter(
                    LocalDateTime.parse(o1.getArrivalTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            );
        }

        return arrivalCity && departureCity && arrivalTime && departureTime;

    }

}
