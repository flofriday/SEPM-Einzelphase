package at.ac.tuwien.sepm.assignment.individual.unit.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.HorseEndpoint;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"test, datagen"})
public abstract class HorseEndpointTestBase {

    @Autowired
    HorseEndpoint horseEndpoint;

    @Autowired
    HorseMapper horseMapper;

    @Test
    @DisplayName("Finding horse by non-existing ID should ResponseStatusException with 404")
    public void findingHorseById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(ResponseStatusException.class,
            () -> horseEndpoint.getOneById(1L));
    }

    @Test
    @DisplayName("Deleteing horse with non-existing ID should ResponseStatusException with 404")
    public void deleteHorseById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(ResponseStatusException.class,
            () -> horseEndpoint.deleteById(1L));
    }

    @Test
    @DisplayName("Searching horses with non-existing name should return no horses")
    public void searchHorses() {
        HorseDto dto = new HorseDto();
        dto.setName("Does not exist");
        assertEquals(horseEndpoint.search(dto).size(), 0);
    }

    @Test
    @DisplayName("Searching horses with non-existing name should return no horses")
    public void addHorse() {
        Horse horse = new Horse();
        horse.setName("Flo");
        horse.setSex(Sex.female);
        horse.setBirthDay(LocalDate.now());
        HorseDto dto = horseMapper.entityToDto(horse);
        horseEndpoint.add(dto);
    }

}
