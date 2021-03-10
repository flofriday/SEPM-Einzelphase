package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface HorseDao {

    /**
     * Get all horses from the database.
     *
     * @return a list with all horses.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data storage.
     */
    List<Horse> getAll();
}
