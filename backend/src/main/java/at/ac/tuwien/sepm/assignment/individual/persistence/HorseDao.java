package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface HorseDao {

    /**
     * Get the horse with given ID.
     *
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the horse could not be found in the database.
     */
    Horse getOneById(Long id) throws NotFoundException;

    /**
     * Get all horses from the database.
     *
     * @return a list with all horses.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data storage.
     */
    List<Horse> getAll();

    /**
     * Add a new horse.
     *
     * @param horse to add to the database.
     * @return the horse added, which is the same as the horse parameter but has now the correct id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Horse add(Horse horse);
}
