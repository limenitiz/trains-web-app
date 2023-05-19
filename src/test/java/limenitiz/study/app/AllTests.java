package limenitiz.study.app;

import limenitiz.study.app.model.*;
import limenitiz.study.app.service.PlaceService;
import limenitiz.study.app.service.TrainService;
import limenitiz.study.templates.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@SpringBootTest(classes = Application.class)
public class AllTests {
    private static final Random r = new Random();

    private static class TestData {
        private static String[] maleUniqueNames = {
                "James", "John", "Robert", "Michael", "William", "David", "Joseph", "Charles", "Thomas", "Daniel",
                "Matthew", "Anthony", "Donald", "Mark", "Paul", "Steven", "Andrew", "Kenneth", "George", "Joshua",
                "Kevin", "Brian", "Edward", "Ronald", "Timothy", "Jason", "Jeffrey", "Frank", "Gary", "Stephen",
                "Eric", "Jacob", "Nicholas", "Jonathan", "Patrick", "Adam", "Larry", "Scott", "Jerry", "Brandon",
                "Samuel", "Benjamin", "Justin", "Raymond", "Gregory", "Henry", "Jack", "Dennis", "Walter", "Peter",
                "Tyler", "Aaron", "Kyle", "Jose", "Jeremy", "Harold", "Keith", "Terry", "Joe", "Bobby",
                "Ryan", "Tim", "Zachary", "Willie", "Ethan", "Billy", "Austin", "Albert", "Arthur", "Phillip",
                "Vincent", "Johnny", "Maurice", "Craig", "Roy", "Howard", "Ralph", "Eugene", "Jordan", "Randy",
                "Louis", "Lawrence", "Alan", "Wayne", "Chris", "Russell", "Clarence", "Arthur", "Nathan", "Lee",
                "Travis", "Danny", "Christian", "Carl", "Sam", "Curtis", "Sean", "Marvin", "Manuel", "Derek"
        };

        private static String[] femaleUniqueNames = {
                "Mary", "Jennifer", "Linda", "Patricia", "Elizabeth", "Susan", "Jessica", "Sarah", "Karen", "Nancy",
                "Lisa", "Margaret", "Betty", "Dorothy", "Sandra", "Ashley", "Kimberly", "Donna", "Emily", "Carol",
                "Michelle", "Amanda", "Melissa", "Deborah", "Stephanie", "Rebecca", "Laura", "Sharon", "Cynthia", "Kathleen",
                "Helen", "Amy", "Anna", "Angela", "Ruth", "Brenda", "Pamela", "Nicole", "Katherine", "Virginia",
                "Catherine", "Christine", "Samantha", "Debra", "Janet", "Rachel", "Carolyn", "Emma", "Maria", "Heather",
                "Diane", "Julie", "Joyce", "Victoria", "Megan", "Christina", "Lauren", "Alice", "Joan", "Evelyn",
                "Olivia", "Martha", "Judith", "Cheryl", "Kelly", "Frances", "Ann", "Jacqueline", "Hannah", "Gloria",
                "Teresa", "Mildred", "Jean", "Doris", "Katherine", "Joan", "Ashley", "Shirley", "Andrea", "Rose",
                "Danielle", "Nicole", "Ruby", "Tammy", "Michelle", "Kayla", "Patricia", "Wanda", "Peggy", "Christine",
                "Marilyn", "Valerie", "Diana", "Shannon", "Donna", "Rita", "Marie", "Wendy", "Beverly", "Alice"
        };

        private static String[] maleUniqueSurnames = {
                "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
                "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson",
                "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King",
                "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter",
                "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins",
                "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey",
                "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James",
                "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson",
                "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler",
                "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers"
        };

        private static String[] femaleUniqueSurnames = {
                "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
                "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson",
                "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King",
                "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter",
                "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins",
                "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey",
                "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James",
                "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson",
                "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler",
                "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers"
        };

        private static String[] arrivalCities = {
                "London", "New York", "Paris", "Tokyo", "Los Angeles", "Berlin", "Rome", "Sydney", "Toronto", "Madrid",
                "Moscow", "Amsterdam", "Vienna", "Beijing", "Dubai", "Stockholm", "Mumbai", "Barcelona", "Singapore", "San Francisco",
                "Cairo", "Istanbul", "Buenos Aires", "Seoul", "Prague", "Athens", "Copenhagen", "Helsinki", "Bangkok", "Lisbon",
                "Brussels", "Dublin", "Oslo", "Warsaw", "Mexico City", "Johannesburg", "Kuala Lumpur", "São Paulo", "Budapest", "Zurich",
                "Vancouver", "Edinburgh", "Auckland", "Havana", "Nairobi", "Reykjavik", "Delhi", "Helsinki", "Manila", "Montreal"
        };

        private static String[] departureCities = {
                "Cape Town", "Rio de Janeiro", "Marrakech", "Seville", "Kyoto", "Florence", "Venice", "Lima", "Munich", "Ottawa",
                "Wellington", "Jerusalem", "Krakow", "St. Petersburg", "Stockholm", "Hamburg", "Nice", "Cologne", "Prague", "Vienna",
                "Dublin", "Brisbane", "Copenhagen", "Sofia", "Bucharest", "Glasgow", "Belgrade", "Ljubljana", "Tallinn", "Helsinki",
                "Zagreb", "Oslo", "Skopje", "Vilnius", "Warsaw", "Budapest", "Reykjavik", "Luxembourg City", "Valletta", "Bratislava"
        };

        public static final PassengerGender[] genders = new PassengerGender[] { PassengerGender.Male, PassengerGender.Female };

        public static final PlaceClass[] classes = new PlaceClass[] { PlaceClass.ReservedSeat, PlaceClass.Compartment };

    }

    private <T> T randChoice(T[] objects) {
        return objects[r.nextInt(0, objects.length)];
    }

    @Autowired private TrainService trainService;
    @Autowired private PlaceService placeService;

    @Test
    void givenTrain_thenSaveIntoDB() {
        for (int train_id = 1; train_id < 19; train_id++) {
            var departureTime = LocalDateTime.now()
                    .plus(r.nextInt(6), ChronoUnit.WEEKS)
                    .plus(r.nextInt(7), ChronoUnit.DAYS)
                    .plus(r.nextInt(24), ChronoUnit.HOURS)
                    .plus(r.nextInt(60), ChronoUnit.MINUTES);

            var arrivalTime = departureTime
                    .plus(r.nextInt(24), ChronoUnit.HOURS)
                    .plus(r.nextInt(60), ChronoUnit.MINUTES);

            trainService.add(Train.builder()
                    .number(String.format("TRN%03d", train_id))
                    .departureCity(randChoice(TestData.departureCities))
                    .arrivalCity(randChoice(TestData.arrivalCities))
                    .departureTime(departureTime)
                    .arrivalTime(arrivalTime)
                    .build());
        }
    }

    @Test
    void givenPlace_thenSaveIntoDB() throws NotFoundException {
        for (long train_id = 1; train_id < 19; train_id++) {
            for (int place_id = 1; place_id < 38; place_id++) {
                var train = trainService.findById(train_id);
                train.getPlaces().add(Place.builder()
                        .build());
            }
        }
    }

    @Test
    void givenPassenger_thenSaveIntoDB() throws NotFoundException {
        var placeCount = placeService.getAll().size();
        for (long place_id = 1; place_id < placeCount; place_id++) {
            if(r.nextBoolean()) {
                var place = placeService.findById(place_id);
                place.setPassenger(Passenger.builder()
                        .build());
            }
        }
    }
}

