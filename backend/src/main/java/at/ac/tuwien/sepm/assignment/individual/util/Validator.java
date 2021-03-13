package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import java.lang.invoke.MethodHandles;

import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.SportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseDao horseDao;
    private final SportDao sportDao;

    @Autowired
    public Validator(HorseDao horseDao, SportDao sportDao) {
        this.horseDao = horseDao;
        this.sportDao = sportDao;
    }

    public void validateNewSport(Sport sport) throws ValidationException {
        LOGGER.trace("validateNewSport({})", sport);

        if (sport.getId() != null) {
            throw new ValidationException("A new sport cannot already have an id.");
        }

        if (sport.getName() == null || sport.getName().strip().isEmpty()) {
            throw new ValidationException("A new sport has to have a name.");
        }

        if (sport.getName().length() > 256) {
            throw new ValidationException("The sport name can only have 256 characters.");
        }

        if (sport.getDescription() != null && sport.getDescription().length() > 1024) {
            throw new ValidationException("The sport description can only have 1024 characters.");
        }
    }

    public void validateNewHorse(Horse horse) throws ValidationException {
        LOGGER.trace("validateNewHorse({})", horse);
        if (horse.getId() != null) {
            throw new ValidationException("A new horse cannot already have an id.");
        }

        validateUpdatedHorse(horse);
    }

    public void validateUpdatedHorse(Horse horse) throws ValidationException {
        LOGGER.trace("validateUpdatedHorse({})", horse);

        // Validate the name and description
        if (horse.getName() == null || horse.getName().strip().isEmpty())
            throw new ValidationException("The horse has to have a name.");

        if (horse.getName().length() > 256)
            throw new ValidationException("The horse name can only have 256 characters.");

        if (horse.getDescription().length() > 1024)
            throw new ValidationException("The horse description can only have 1024 characters.");

        // Validate the favorite sport
        if (horse.getFavoriteSportId() != null) {
            try {
                sportDao.getOneById(horse.getFavoriteSportId());
            } catch (NotFoundException e) {
                throw new ValidationException("The favorite sport of a horse must exist.");
            } catch (PersistenceException e)  {
                throw new ValidationException(e);
            }
        }

        // Validate the parents
        if (horse.getId() != null && (horse.getId() == horse.getFatherId() || horse.getId() == horse.getMotherId()))
            throw new ValidationException("The horse cannot be its own parent.");

        if (horse.getMotherId() != null) {
            Horse mother;
            try {
                mother = horseDao.getOneById(horse.getMotherId());
            } catch (NotFoundException e) {
                throw new ValidationException("The mother of a horse must exist.");
            } catch (PersistenceException e)  {
                throw new ValidationException(e);
            }

            if (mother.getSex() != Sex.female)
                throw new ValidationException("The mother of a horse must be female.");

            if (mother.getBirthDay().isAfter(horse.getBirthDay()))
                throw new ValidationException("The horse cannot be older than its own mother.");
        }

        if (horse.getFatherId() != null) {
            Horse father;
            try {
                father = horseDao.getOneById(horse.getFatherId());
            } catch (NotFoundException e) {
                throw new ValidationException("The mother of a horse must exist.");
            } catch (PersistenceException e)  {
                throw new ValidationException(e);
            }

            if (father.getSex() != Sex.male)
                throw new ValidationException("The father of a horse must be male.");

            if (father.getBirthDay().isAfter(horse.getBirthDay()))
                throw new ValidationException("The horse cannot be older than its own father.");
        }
    }
}
