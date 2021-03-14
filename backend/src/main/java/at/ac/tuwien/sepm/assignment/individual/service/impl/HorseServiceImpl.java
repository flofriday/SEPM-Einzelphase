package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class HorseServiceImpl implements HorseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseDao dao;
    private final Validator validator;

    @Autowired
    public HorseServiceImpl(HorseDao horseDao, Validator validator) {
        this.dao = horseDao;
        this.validator = validator;
    }

    @Override
    public Horse getOneById(Long id) throws NotFoundException {
        LOGGER.trace("getOneById({})", id);
        try {
            return dao.getOneById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Horse> getAll() {
        LOGGER.trace("getall()");
        try {
            return dao.getAll();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Horse add(Horse horse) throws ValidationException {
        LOGGER.trace("add({})", horse);

        // The sport has to be valid
        validator.validateNewHorse(horse);

        // Remove leading and trailing whitespaces
        horse.setName(horse.getName().strip());
        horse.setDescription(horse.getDescription().strip());

        // Description should not be an empty string but null
        if (horse.getDescription().isEmpty()) horse.setDescription(null);

        try {
            return dao.add(horse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Horse update(Horse horse) throws ValidationException {
        LOGGER.trace("update({})", horse);

        // The sport has to be valid
        validator.validateUpdatedHorse(horse);

        // Remove leading and trailing whitespaces
        horse.setName(horse.getName().strip());
        horse.setDescription(horse.getDescription().strip());

        // Description should not be an empty string but null
        if (horse.getDescription().isEmpty()) horse.setDescription(null);

        try {
            return dao.update(horse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
