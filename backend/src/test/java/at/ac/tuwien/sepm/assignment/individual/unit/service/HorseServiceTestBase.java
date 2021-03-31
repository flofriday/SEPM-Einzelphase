package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.SportDao;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.service.SportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "datagen"})
public abstract class HorseServiceTestBase {

    @Autowired
    HorseService horseService;

    @Test
    @DisplayName("Creating a sport with an emtpy name should throw an Exception")
    public void addNewSport_inValidnoName_shouldThrowValidationException() {
        Sport sport = new Sport(null, "    ");
        sport.setDescription("Something");
        assertThrows(ValidationException.class, () -> horseService.add(horse));
    }

    @Test
    @DisplayName("Creating a sport with an id should throw an Exception")
    public void addNewSport_inValidExistingId_shouldThrowValidationException() {
        Sport sport = new Sport(34L, "Supersport");
        sport.setDescription("Something");
        assertThrows(ValidationException.class, () -> horseService.add(horse));
    }
}
