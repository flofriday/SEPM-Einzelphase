package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.SportDao;
import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SportJdbcDao implements SportDao {

    private static final String TABLE_NAME = "sport";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SportJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sport getOneById(Long id) throws NotFoundException{
        LOGGER.trace("getOneById({})", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Sport> sports;
        try {
            sports = jdbcTemplate.query(sql, this::mapRow, id);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        if (sports.isEmpty()) throw new NotFoundException("Could not find sport with id " + id);

        return sports.get(0);
    }

    @Override
    public List<Sport> getAll() {
        LOGGER.trace("getAll()");
        final String sql = "SELECT * FROM " + TABLE_NAME;
        List<Sport> sports;

        try {
            sports = jdbcTemplate.query(sql, this::mapRow);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }
        return sports;
    }

    @Override
    public Sport add(Sport sport) {
        LOGGER.trace("add({})", sport);
        final String sql = "INSERT INTO " + TABLE_NAME + " (name, description) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, sport.getName());
                stmt.setString(2, sport.getDescription());
                return stmt;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        sport.setId(((Number)keyHolder.getKeys().get("id")).longValue());
        return sport;
    }


    private Sport mapRow(ResultSet resultSet, int i) throws SQLException {
        final Sport sport = new Sport();
        sport.setId(resultSet.getLong("id"));
        sport.setName(resultSet.getString("name"));
        sport.setDescription(resultSet.getString("description"));
        return sport;
    }
}
