package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SportDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import org.springframework.stereotype.Component;

@Component
public class SportMapper {

    public SportDto entityToDto(Sport sport) {
        if (sport == null)
            return null;
        return new SportDto(sport.getId(), sport.getName());
    }

}
