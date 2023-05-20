package limenitiz.study.app.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class TrainExample extends AbstractTrain {
    public TrainExample() {
    }

    public TrainExample(Long id, String number,
                        String arrivalCity, String departureCity,
                        LocalDateTime arrivalTime, LocalDateTime departureTime,
                        List<Place> places) {
        super(id, number,
                arrivalCity, departureCity,
                arrivalTime, departureTime,
                places);
    }

    public Train toTrain() {
        clearFields();
        return new Train(
            this.id, this.number,
                this.arrivalCity, this.departureCity,
                this.arrivalTime, this.departureTime,
                this.places
        );
    }

    public TrainExpress toTrainExpress() {
        clearFields();
        return new TrainExpress(
                this.id, this.number,
                this.arrivalCity, this.departureCity,
                this.arrivalTime, this.departureTime,
                this.places
        );
    }

    private void clearFields() {
        if ("".equals(arrivalCity)) {
            this.arrivalCity = null;
        }
        if ("".equals(departureCity)) {
            this.departureCity = null;
        }
    }
}
