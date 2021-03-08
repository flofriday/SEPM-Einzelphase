package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SportDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SportMapper {

    public SportDto entityToDto(Sport sport) {
        if (sport == null)
            return null;
        return new SportDto(sport.getId(), sport.getName(), sport.getDescription());
    }

    public Sport dtoToEntity(SportDto sport) {
        if (sport == null)
            return null;
        return new Sport(sport.getId(), sport.getName(), sport.getDescription());
    }

    public List<SportDto> entityListToDto(List<Sport> sportList) {
        List<SportDto> dtos = new ArrayList<>();
        for (Sport sport: sportList) {
            dtos.add(this.entityToDto(sport));
        }
        return dtos;
    }

}
