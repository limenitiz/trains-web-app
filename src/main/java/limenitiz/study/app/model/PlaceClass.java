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

    public String getValWithoutSpaces() {
        return val.replaceAll(" ", "");
    }

    @Override
    public String toString() {
        return getVal();
    }
}
