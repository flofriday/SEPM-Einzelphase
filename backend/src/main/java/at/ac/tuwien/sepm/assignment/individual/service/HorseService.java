package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;

import java.util.List;

public interface HorseService {

    /**
     * Gets all horses.
     *
     * @return a list with all horses.
     * @throws ServiceException  if something goes wrong during data processing.
     */
    List<Horse> getAll();
}
