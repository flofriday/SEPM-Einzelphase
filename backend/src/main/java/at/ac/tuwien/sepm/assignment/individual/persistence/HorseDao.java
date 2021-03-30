package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.HorseTree;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

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
     * Get a list of matching horses.
     * A horse is considered matching if all parameters that are not null are the same. For strings the horse it the
     * storage only has to contain the string in the entity object.
     * Following fields will not be considered: id, motherID, fatherID
     *
     * @return a list with all horses that match the horse parameter.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data storage.
     */
    List<Horse> search(Horse horse);

    /**
     * Familiy tree of a specified horse.
     * The tree only contains ancestors.
     *
     * @param id    of the horse to create the tree for.
     * @param depth of the tree.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws NotFoundException    if the horse could not be found in the system.
     */
    HorseTree getTree(Long id, Integer depth) throws ValidationException;


    /**
     * Get all children from a horse from the database.
     *
     * @return a list with the children.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data storage.
     */
    List<Horse> getChildren(Horse horse);

    /**
     * Add a new horse.
     *
     * @param horse to add to the database.
     * @return the horse added, which is the same as the horse parameter but has now the correct id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Horse add(Horse horse);

    /**
     * Update an exisitng horse.
     *
     * @param horse to update.
     * @return the horse updated, which might be slightly changed from the horse param.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Horse update(Horse horse);

    /**
     * Delete an exisitng horse.
     *
     * @param id of the horse ot delete.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    void delete(long id);

}
