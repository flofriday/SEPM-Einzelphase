package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.HorseTree;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

import java.util.List;

public interface HorseService {

    /**
     * Gets the horse with a given ID.
     *
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException     if something goes wrong during data processing.
     * @throws NotFoundException    if the horse could not be found in the system.
     */
    Horse getOneById(Long id) throws NotFoundException;

    /**
     * Search horses.
     *
     * @return a list with all matching horses.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException     if something goes wrong during data processing.
     */
    List<Horse> search(Horse horse);

    /**
     * Familiy tree of a specified horse.
     * The tree only contains ancestors.
     *
     * @param id    of the horse to create the tree for.
     * @param depth of the tree.
     * @throws ValidationException  if the input is not valid.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException     if something goes wrong during data processing.
     * @throws NotFoundException    if the horse could not be found in the system.
     */
    HorseTree getTree(Long id, Integer depth) throws ValidationException;

    /**
     * Add a new horse.
     *
     * @param horse to add to the storage.
     * @return a modified version of the horse parameter, how it was actually stored.
     * @throws ValidationException  if the input is not valid.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException     if something goes wrong during data processing.
     */
    Horse add(Horse horse) throws ValidationException;

    /**
     * Update an existing horse.
     *
     * @param horse to update.
     * @return a modified version of the horse parameter, how it was actually stored.
     * @throws ValidationException  if the input is not valid.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException     if something goes wrong during data processing.
     */
    Horse update(Horse horse) throws ValidationException, NotFoundException;

    /**
     * Delete the horse with a given ID.
     *
     * @param id of the horse to delete.
     * @return the horse with the specified id.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException     if something goes wrong during data processing.
     * @throws NotFoundException    if the horse could not be found in the system.
     * @throws ValidationException  if the input cannot be deleted.
     */
    void deleteById(Long id) throws NotFoundException, ValidationException;
}
