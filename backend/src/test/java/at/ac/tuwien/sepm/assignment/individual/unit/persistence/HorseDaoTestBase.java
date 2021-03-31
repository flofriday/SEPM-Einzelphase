package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "datagen"})
public abstract class HorseDaoTestBase {

    @Autowired
    HorseDao horsedao;

    @Test
    @DisplayName("Delte horse with non-existing id should throw NotFoundException")
    public void deleteHorse_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> horsedao.delete(-1L));
    }

    @Test
    @DisplayName("Search female horses return right number of horses")
    public void search_female_horses_existing() {
        Horse horse = new Horse();
        horse.setSex(Sex.female);
        assertTrue(horsedao.search(horse).size() == 4);
    }

}
