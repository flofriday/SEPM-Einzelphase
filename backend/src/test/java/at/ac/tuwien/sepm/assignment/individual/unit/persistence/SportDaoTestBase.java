package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.SportDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "datagen"})
public abstract class SportDaoTestBase {

    @Autowired
    SportDao sportDao;

    @Test
    @DisplayName("Finding sport by non-existing ID should throw NotFoundException")
    public void findingSportById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> sportDao.getOneById(1L));
    }

    @Test
    @DisplayName("Finding sport by ID and compare result")
    public void findingSportById_existing() throws Exception {
        Sport sport = new Sport(-1L, "Polo");
        assertEquals(sport , sportDao.getOneById(-1L));
    }

}
