package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseTreeDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseMapper;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseTreeMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(HorseEndpoint.BASE_URL)
public class HorseEndpoint {

    static final String BASE_URL = "/horses";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseService horseService;
    private final HorseMapper horseMapper;
    private final HorseTreeMapper horseTreeMapper;

    @Autowired
    public HorseEndpoint(HorseService horseService, HorseMapper horseMapper, HorseTreeMapper horseTreeMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
        this.horseTreeMapper = horseTreeMapper;
    }

    @GetMapping(value = "/{id}")
    public HorseDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return horseMapper.entityToDto(horseService.getOneById(id));
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(value = "")
    public List<HorseDto> search(HorseDto dto) {
        LOGGER.info("GET " + BASE_URL);

        Horse horse;
        try {
            horse = horseMapper.dtoToEntity(dto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (horse == null) {
            horse = new Horse();
            LOGGER.info("null");
        }

        return horseMapper.entityListToDto(horseService.search(horse));
    }

    @GetMapping(value = "/{id}/tree")
    public HorseTreeDto getTree(@PathVariable("id") Long id, @RequestParam("depth") Integer depth) {
        LOGGER.info("GET " + BASE_URL + "/{}/tree", id);

        // Default depth value is 3
        if (depth == null)
            depth = 3;

        // TODO: check exceptions here
        try {
            return horseTreeMapper.entityToDto(horseService.getTree(id, depth));
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "")
    public HorseDto add(@RequestBody HorseDto horseDto) {
        LOGGER.info("POST " + BASE_URL);

        Horse horse;
        try {
            horse = horseMapper.dtoToEntity(horseDto);
        } catch (Exception e) {
            // TODO: Is this the right response?
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            return horseMapper.entityToDto(horseService.add(horse));
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{id}")
    public HorseDto update(@PathVariable("id") Long id, @RequestBody HorseDto horseDto) {
        LOGGER.info("PUT " + BASE_URL + "/{}", id);

        Horse horse;
        try {
            // TODO: that dowsn't seam right
            horse = horseMapper.dtoToEntity(horseDto);
            horse.setId(id);
        } catch (Exception e) {
            // TODO: Is this the right response?
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            return horseMapper.entityToDto(horseService.update(horse));
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", id);
        try {
            horseService.deleteById(id);
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage(), e);
            String message = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, message, e);
        }
    }
}
