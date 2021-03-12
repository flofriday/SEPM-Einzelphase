package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import java.lang.invoke.MethodHandles;

import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
        if (horse.getId() != null) {
            throw new ValidationException("A new horse cannot already have an id.");
        }

        // TODO: Add missing validation
        // Note: We could now call the validateUpdatedHorse() method
    }
}
