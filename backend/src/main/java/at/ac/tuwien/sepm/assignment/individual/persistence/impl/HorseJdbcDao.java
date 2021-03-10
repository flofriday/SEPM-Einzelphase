package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Long getLongObject(ResultSet resultSet, String field) throws SQLException{
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
