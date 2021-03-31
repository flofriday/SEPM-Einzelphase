package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class HorseServiceTestBase {

    @Autowired
    HorseService horseService;

    @Test
    @DisplayName("Deleteing nonexisting Horse should throw NotFoundException")
    public void deleteNonexistingHorse() {
        assertThrows(NotFoundException.class,
            () -> horseService.deleteById(42L));
    }

    @Test
    @DisplayName("Creating Horse with empty name results in ValidationExeption")
    public void addHorse_missingSex() {
        Horse horse = new Horse();
        horse.setName("Flotschi");
        horse.setBirthDay(LocalDate.now());
        assertThrows(ValidationException.class,
            () -> horseService.add(new Horse()));
    }

}