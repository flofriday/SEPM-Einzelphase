package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SportDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.SportMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.service.SportService;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(SportEndpoint.BASE_URL)
public class SportEndpoint {

    static final String BASE_URL = "/sports";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final SportService sportService;
    private final SportMapper sportMapper;

    @Autowired
    public SportEndpoint(SportService sportService, SportMapper sportMapper) {
        this.sportService = sportService;
        this.sportMapper = sportMapper;
    }

    @GetMapping(value = "/{id}")
    public SportDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return sportMapper.entityToDto(sportService.getOneById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading sport", e);
        }
    }

    @GetMapping(value = "")
    public List<SportDto> getAll() {
        LOGGER.info("GET " + BASE_URL);
        return sportMapper.entityListToDto(sportService.getAll());
    }

    @PostMapping(value = "")
    public SportDto add(@RequestBody SportDto sportDto) {
        LOGGER.info("POST " + BASE_URL);
        Sport sport = sportMapper.dtoToEntity(sportDto);
        try {
            return sportMapper.entityToDto(sportService.add(sport));
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during validating the new sport", e);
        }
    }
}
