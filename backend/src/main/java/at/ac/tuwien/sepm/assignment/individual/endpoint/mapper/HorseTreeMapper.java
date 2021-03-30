package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseTreeDto;
import at.ac.tuwien.sepm.assignment.individual.entity.HorseTree;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class HorseTreeMapper {
    public HorseTreeDto entityToDto(HorseTree horse) {
        if (horse == null)
            return null;
        HorseTreeDto dto = new HorseTreeDto();
        dto.setId(horse.getId());
        dto.setName(horse.getName());
        dto.setDescription(horse.getDescription());
        dto.setBirthDay(DateTimeFormatter.ISO_DATE.format(horse.getBirthDay()));
        dto.setSex(horse.getSex().toString());
        dto.setFavoriteSportId(horse.getFavoriteSportId());
        dto.setFavoriteSportName(horse.getFavoriteSportName());
        dto.setFather(this.entityToDto(horse.getFather()));
        dto.setMother(this.entityToDto(horse.getMother()));
        return dto;
    }
}
