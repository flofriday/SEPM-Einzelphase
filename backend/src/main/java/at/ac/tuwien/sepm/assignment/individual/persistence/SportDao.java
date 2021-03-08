package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface SportDao {

    /**
     * Get the sport with given ID.
     *
     * @param id of the sport to find.
     * @return the sport with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException   will be thrown if the sport could not be found in the database.
     */
    Sport getOneById(Long id) throws NotFoundException;

    /**
     * Get all sports from the database
     *
     * @return the list with all sports.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    List<Sport> getAll();

    /**
     * Add a new sport.
     *
     * @param sport to add to the database.
     * @return the sport added, which is the same as the sport parameter but has now the correct id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Sport add(Sport sport);
}
