package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

import java.util.List;

public interface SportService {

    /**
     * Gets the sport with a given ID.
     *
     * @param id of the sport to find.
     * @return the sport with the specified id.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException  if something goes wrong during data processing.
     * @throws NotFoundException if the sport could not be found in the system.
     */
    Sport getOneById(Long id) throws NotFoundException;

    /**
     * Gets all sports.
     *
     * @return a list with all sports.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException  if something goes wrong during data processing.
     */
    List<Sport> getAll();

    /**
     * Add a new sport.
     *
     * @param sport to add to the storage.
     * @return a modified version of the sport parameter, how it was actually stored.
     * @throws ValidationException if the input is not valid.
     * @throws PersistenceException if something goes wrong at the persitence layer.
     * @throws ServiceException  if something goes wrong during data processing.
     */
    Sport add(Sport sport) throws ValidationException;
}
