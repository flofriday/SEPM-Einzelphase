package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.List;

@Repository
public class HorseJdbcDao implements HorseDao {
    private static final String TABLE_NAME = "horse";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HorseJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Horse getOneById(Long id) throws NotFoundException {
        LOGGER.trace("getOneById({})", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Horse> horses;
        try {
            horses = jdbcTemplate.query(sql, this::mapRow, id);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        if (horses.isEmpty()) throw new NotFoundException("Could not find horse with id " + id);

        return horses.get(0);
    }

    @Override
    public List<Horse> getAll() {
        LOGGER.trace("getAll()");
        final String sql = "SELECT * FROM " + TABLE_NAME;
        List<Horse> horses;

        try {
            horses = jdbcTemplate.query(sql, this::mapRow);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }
        return horses;
    }

    @Override
    public Horse add(Horse horse) {
        LOGGER.trace("add({})", horse);
        final String sql = "INSERT INTO " + TABLE_NAME +
            " (name, description, birthday, sex, favoriteSport, mother, father) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            //sports = jdbcTemplate.query(sql, this::mapRow, sport.getName(), sport.getDescription());
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, horse.getName());
                stmt.setString(2, horse.getDescription());
                stmt.setDate(3, Date.valueOf(horse.getBirthDay()));
                stmt.setString(4, horse.getSex().toString());

                if (horse.getFavoriteSportId() != null) {
                    stmt.setLong(5, horse.getFavoriteSportId());
                } else {
                    stmt.setNull(5, Types.BIGINT);
                }

                if (horse.getMotherId() != null) {
                    stmt.setLong(6, horse.getMotherId());
                } else {
                    stmt.setNull(6, Types.BIGINT);
                }

                if (horse.getFatherId() != null) {
                    stmt.setLong(7, horse.getFatherId());
                } else {
                    stmt.setNull(7, Types.BIGINT);
                }

                return stmt;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        horse.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        return horse;
    }

    @Override
    public Horse update(Horse horse) {
        LOGGER.trace("update({})", horse);
        final String sql = "UPDATE " + TABLE_NAME +
            " SET name=?, description=?, birthday=?, sex=?, favoriteSport=?, mother=?, father=?" +
            "WHERE id=?";
        //KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql,
                horse.getName(),
                horse.getDescription(),
                horse.getBirthDay().toString(),
                horse.getSex().toString(),
                horse.getFavoriteSportId(),
                horse.getMotherId(),
                horse.getFatherId(),
                horse.getId()
            );
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        return horse;
    }

    private Long getLongObject(ResultSet resultSet, String field) throws SQLException {
        Object obj = resultSet.getObject(field);
        if (obj == null) return null;
        return (Long) obj;
    }

    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setDescription(resultSet.getString("description"));
        horse.setSex(Sex.valueOf(resultSet.getString("sex")));
        horse.setBirthDay(resultSet.getDate("birthday").toLocalDate());
        horse.setFavoriteSportId(getLongObject(resultSet, "favoriteSport"));
        horse.setMotherId(getLongObject(resultSet, "mother"));
        horse.setFatherId(getLongObject(resultSet, "father"));
        return horse;
    }
}
