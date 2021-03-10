package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;

import java.util.List;

public interface HorseService {

    /**
     * Gets the horse with a given ID.
     *
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws ServiceException  if something goes wrong during data processing.
     * @throws NotFoundException if the horse could not be found in the system.
     */
    Horse getOneById(Long id) throws NotFoundException;

    /**
     * Gets all horses.
     *
     * @return a list with all horses.
     * @throws ServiceException if something goes wrong during data processing.
     */
    List<Horse> getAll();
}
