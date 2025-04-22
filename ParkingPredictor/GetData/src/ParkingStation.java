public class ParkingStation {

    // define useful attributes
    String name;
    String code;
    int free_limit;
    String coordinate_x;
    String coordinate_y;
    int capacity;
    String municipality;
    boolean charging;
    boolean surveillance;
    int free_lots;
    int occupied_lots;
    int mperiod;
    int _timestamp;

    public ParkingStation(String name, String code, int free_limit, String coordinate_x, String coordinate_y, int capacity, String municipality, boolean charging, boolean surveillance, int free_lots, int occupied_lots, int mperiod, int _timestamp) {
        this.name = name;
        this.code = code;
        this.free_limit = free_limit;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.capacity = capacity;
        this.municipality = municipality;
        this.charging = charging;
        this.surveillance = surveillance;
        this.free_lots = free_lots;
        this.occupied_lots = occupied_lots;
        this.mperiod = mperiod;
        this._timestamp = _timestamp;
    }

    // add getters and setters
}
