package at.ac.tuwien.sepm.assignment.individual.util;

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

        if (sport.getName() == null || sport.getName().strip().isEmpty()) {
            throw new ValidationException("A sport has to have a name.");
        }
    }
}
