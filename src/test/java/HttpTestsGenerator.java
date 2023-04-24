import limenitiz.study.app.model.PassengerGender;
import limenitiz.study.app.model.PlaceClass;

import java.util.Random;

public class HttpTestsGenerator {
    public static final Random r = new Random();
    public static final PlaceClass[] classes = new PlaceClass[] { PlaceClass.ReservedSeat, PlaceClass.Compartment };

    public static final String[] uniqueNames = {
            "Ethan", "Ava", "Benjamin", "Olivia", "Samuel", "Charlotte", "Daniel", "Sophia", "Alexander", "Amelia",
            "Matthew", "Harper", "Jackson", "Evelyn", "William", "Grace", "James", "Abigail", "Benjamin", "Emily",
            "Henry", "Elizabeth", "Michael", "Sofia", "Elijah", "Scarlett", "Daniel", "Victoria", "David", "Lily",
            "Joseph", "Zoey", "Samuel", "Penelope", "Andrew", "Hannah", "Jack", "Natalie", "Gabriel", "Addison",
            "Isaac", "Mia", "Christopher", "Layla", "John", "Chloe", "Oliver", "Aubrey", "Jonathan", "Zoey",
            "Luke", "Madeline", "Joshua", "Aubrey", "Anthony", "Harper", "Nathaniel", "Eleanor", "Liam", "Grace",
            "Caleb", "Victoria", "Christian", "Stella", "Isaac", "Leah", "Evan", "Hannah", "Ryan", "Sofia",
            "Julian", "Addison", "Aiden", "Ellie", "Owen", "Savannah", "Connor", "Aurora", "Jeremiah", "Ruby",
            "Josiah", "Natalie", "Jonathan", "Ariana", "Aaron", "Kennedy", "Xavier", "Paisley", "Adam", "Gabriella",
            "Cameron", "Claire", "Dylan", "Skylar", "Joshua", "Piper", "Landon", "Julia", "Cole", "Brooke"
    };

    public static final String[] uniqueLastNames = {
            "Smith", "Johnson", "Brown", "Taylor", "Miller", "Anderson", "Thomas", "Jackson", "White", "Harris",
            "Clark", "Lewis", "Lee", "Hall", "Young", "Walker", "Allen", "King", "Wright", "Turner",
            "Parker", "Collins", "Morris", "Cook", "Rogers", "Reed", "Bailey", "Cooper", "Morgan", "Kelly",
            "Howard", "Ward", "Cox", "Diaz", "Richardson", "Wood", "Watson", "Brooks", "Bennett", "Gray",
            "James", "Reyes", "Cruz", "Hughes", "Price", "Myers", "Long", "Foster", "Sanders", "Ross",
            "Morales", "Powell", "Sullivan", "Russell", "Ortiz", "Jenkins", "Gomez", "Murray", "Freeman", "Wells",
            "Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter", "Hicks", "Crawford", "Henry", "Boyd",
            "Mason", "Morris", "Kennedy", "Warren", "Dixon", "Ramos", "Reyes", "Burns", "Gordon", "Shaw",
            "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels", "Palmer", "Mills", "Nichols", "Grant",
            "Knight", "Ferguson", "Rose", "Stone", "Hawkins", "Dunn", "Perkins", "Hudson", "Spencer", "Gardner",
            "Stephens", "Payne", "Pierce", "Berry", "Matthews", "Arnold", "Wagner", "Willis", "Ray", "Watkins"
    };

    public static final PassengerGender[] genders = new PassengerGender[] { PassengerGender.Male, PassengerGender.Female };



    public static void genTrainPlaces() {

        String template = """
                ### Add place
                POST http://localhost:8080/api/place/insert/train/${train-id}
                Content-Type: application/json
                                
                {
                  "number": ${number},
                  "placeClass": "${class}",
                  "price": ${price}
                }
                                
                > {%
                  client.test("Request executed successfully", function() {
                    client.assert(response.status === 200, "Response status is not 200");
                  });
                %}
                
                
                """;

        // [1, 18] train id
        for (int train_id = 1; train_id < 19; train_id++) {
            // [10, 50] places count
            for (int number = 0; number < r.nextInt(10, 50); number++) {
                var placeClass = classes[r.nextInt(0,2)];
                var placeCoefficient = switch (placeClass) {
                    case ReservedSeat -> 10;
                    case Compartment -> 1;
                };
                System.out.println(template
                        .replace("${train-id}", String.valueOf(train_id))
                        .replace("${number}", String.valueOf(number))
                        .replace("${class}", String.valueOf(placeClass))
                        .replace("${price}", String.valueOf(r.nextDouble(
                                100 * placeCoefficient,
                                500 * placeCoefficient)))
                );
            }
        }
    }

    public static void genTrainExpressPlaces() {
        String template = """
                ### Add place
                POST http://localhost:8080/api/place/insert/train-express/${train-id}
                Content-Type: application/json
                                
                {
                  "number": ${number},
                  "placeClass": "${class}",
                  "price": ${price}
                }
                                
                > {%
                  client.test("Request executed successfully", function() {
                    client.assert(response.status === 200, "Response status is not 200");
                  });
                %}
                
                
                """;

        // [1, 18] train id
        for (int train_id = 1; train_id < 7; train_id++) {
            // [10, 50] places count
            for (int number = 0; number < r.nextInt(10, 50); number++) {
                var placeClass = classes[r.nextInt(0,2)];
                var placeCoefficient = switch (placeClass) {
                    case ReservedSeat -> 10;
                    case Compartment -> 1;
                };
                System.out.println(template
                        .replace("${train-id}", String.valueOf(train_id))
                        .replace("${number}", String.valueOf(number))
                        .replace("${class}", String.valueOf(placeClass))
                        .replace("${price}", String.valueOf(r.nextDouble(
                                100 * placeCoefficient,
                                500 * placeCoefficient)))
                );
            }
        }
    }


    public static void genPlacesPassengers() {

        String template = """
                            ### Add passenger
                            POST http://localhost:8080/api/passenger/insert/place/${place-id}
                            Content-Type: application/json
                            
                            {
                              "firstName": "${name-1}",
                              "secondName": "${name-2}",
                              "gender": "${gender}"
                            }
                            
                            > {%
                                client.test("Request executed successfully", function() {
                                    client.assert(response.status === 200, "Response status is not 200");
                                });
                            %}
                            
                            
                            """;

        for (int place_id = 1; place_id < 402; place_id++) {
            if(r.nextBoolean()) {
                System.out.println(template
                        .replace("${place-id}", String.valueOf(place_id))
                        .replace("${name-1}", uniqueNames[r.nextInt(0, uniqueNames.length)])
                        .replace("${name-2}", uniqueLastNames[r.nextInt(0, uniqueLastNames.length)])
                        .replace("${gender}", genders[r.nextInt(0, genders.length)].toString())
                );
            }
        }
    }

    public static void main(String[] args) {
//        genTrainPlaces();
//        genTrainExpressPlaces();
//        genPlacesPassengers();
    }
}
