package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class HorseMapper {

    public HorseDto entityToDto(Horse horse) {
        if (horse == null)
            return null;
        HorseDto dto = new HorseDto();
        dto.setId(horse.getId());
        dto.setName(horse.getName());
        dto.setDescription(horse.getDescription());
        dto.setBirthDay(DateTimeFormatter.ISO_DATE.format(horse.getBirthDay()));
        dto.setSex(horse.getSex().toString());
        dto.setFavoriteSportId(horse.getFavoriteSportId());
        dto.setMotherId(horse.getMotherId());
        dto.setFatherId(horse.getFatherId());
        return dto;
    }

    public Horse dtoToEntity(HorseDto dto) {
        if (dto == null)
            return null;
        Horse horse = new Horse();
        horse.setId(dto.getId());
        horse.setName(dto.getName());
        horse.setDescription(dto.getDescription());
        horse.setBirthDay(LocalDate.parse(dto.getBirthDay()));
        horse.setSex(Sex.valueOf(dto.getSex()));
        horse.setFavoriteSportId(dto.getFavoriteSportId());
        horse.setMotherId(dto.getMotherId());
        horse.setFatherId(dto.getFatherId());
        return horse;
    }

    public List<HorseDto> entityListToDto(List<Horse> horseList) {
        List<HorseDto> dtos = new ArrayList<>();
        for (Horse horse : horseList) {
            dtos.add(this.entityToDto(horse));
        }
        return dtos;
    }

}
