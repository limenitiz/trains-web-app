package limenitiz.study.app.model;


public enum PlaceClass {

    ReservedSeat("Reserved Seat"),
    Compartment("Compartment");

    private final String val;

    PlaceClass(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
