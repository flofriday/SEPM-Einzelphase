package at.ac.tuwien.sepm.assignment.individual.base;


import at.ac.tuwien.sepm.assignment.individual.entity.Sport;

public interface TestData {

    /**
     * URI Data
     */
    String BASE_URL = "http://localhost:";
    String HORSE_URL = "/horses";
    String SPORT_URL = "/sports";

    /**
     * Sport Data
     */
    static Sport getNewSport() {
        return new Sport("Sport");
    }

    static Sport getNewSport(String name) {
        return new Sport(name);
    }

    static Sport getNewSportWithId() {
        return new Sport(1L, "Sport");
    }


}
