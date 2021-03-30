package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.HorseTree;
import at.ac.tuwien.sepm.assignment.individual.entity.Sex;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final String sql = "SELECT horse.*, " +
            "sport.name AS favoriteSportName, " +
            "mother.name AS motherName, " +
            "father.name AS fatherName " +
            "FROM horse " +
            "LEFT JOIN sport " +
            "ON horse.favoritesport = sport.id " +
            "LEFT JOIN horse AS mother " +
            "ON horse.mother = mother.id " +
            "LEFT JOIN horse AS father " +
            "ON horse.father = father.id " +
            "WHERE horse.id  = ?";

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
    public List<Horse> search(Horse horse) {
        LOGGER.trace("search()");
        final String sql =
            "SELECT horse.*, " +
                "sport.name AS favoriteSportName, " +
                "mother.name AS motherName, " +
                "father.name AS fatherName " +
                "FROM horse " +
                "LEFT JOIN sport " +
                "ON horse.favoritesport = sport.id " +
                "LEFT JOIN horse AS mother " +
                "ON horse.mother = mother.id " +
                "LEFT JOIN horse AS father " +
                "ON horse.father = father.id " +
                "WHERE (? IS NULL OR LOWER(horse.name) LIKE LOWER(?))" +
                " AND (? IS NULL OR LOWER(horse.description) LIKE LOWER(?))" +
                " AND (? IS NULL OR horse.birthday < ?)" +
                " AND (? IS NULL OR horse.sex = ?)" +
                " AND (? IS NULL OR horse.favoriteSport = ?)";
        List<Horse> horses;

        String sex = horse.getSex() == null ? null : horse.getSex().toString();
        LOGGER.trace("search()");
        try {
            horses = jdbcTemplate.query(sql, this::mapRow,
                horse.getName(), "%" + horse.getName() + "%",
                horse.getDescription(), "%" + horse.getDescription() + "%",
                horse.getBirthDay(), horse.getBirthDay(),
                sex, sex,
                horse.getFavoriteSportId(), horse.getFavoriteSportId());
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }
        return horses;
    }

    @Override
    public HorseTree getTree(Long id, Integer depth) throws ValidationException {
        LOGGER.trace("getTree({}, {})", id, depth);
        String sql = "WITH RECURSIVE ancestor(id, name, description, birthday, sex, favoritesport, mother, father, depth) AS ( " +
            "SELECT *, 1 as depth " +
            "FROM horse " +
            "WHERE id = ? " +
            "UNION ALL " +
            "SELECT horse.*, (ancestor.depth + 1) " +
            "FROM horse, ancestor " +
            "WHERE (ancestor.mother = horse.id OR ancestor.father = horse.id) AND ancestor.depth < ? " +
            ") " +
            "SELECT ancestor.*, sport.name AS favoriteSportName FROM ancestor " +
            "LEFT JOIN sport " +
            "ON ancestor.favoritesport = sport.id;";

        List<Horse> horses;
        try {
            horses = jdbcTemplate.query(sql, this::mapTreeRow, id, depth);
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        HashMap<Long, Horse> map = new HashMap<>();
        for (Horse horse : horses) {
            map.put(horse.getId(), horse);
        }

        HorseTree tree = horsesToTree(map, id);
        return tree;
    }

    @Override
    public List<Horse> getChildren(Horse horse) {
        LOGGER.trace("getChildren({})", horse);
        final String sql =
            "SELECT horse.*, " +
                "sport.name AS favoriteSportName, " +
                "mother.name AS motherName, " +
                "father.name AS fatherName " +
                "FROM horse " +
                "LEFT JOIN sport " +
                "ON horse.favoritesport = sport.id " +
                "LEFT JOIN horse AS mother " +
                "ON horse.mother = mother.id " +
                "LEFT JOIN horse AS father " +
                "ON horse.father = father.id " +
                "WHERE horse.father=? OR horse.mother=?";
        List<Horse> horses;

        try {
            horses = jdbcTemplate.query(sql, this::mapRow, horse.getId(), horse.getId());
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

    @Override
    public void delete(long id) {
        LOGGER.trace("delete({})", id);
        final String sql = "DELETE FROM " + TABLE_NAME +
            " WHERE id=?";
        int affectedRows;

        try {
            affectedRows = jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, id);
                return stmt;
            });
        } catch (DataAccessException e) {
            throw new PersistenceException(e);
        }

        if (affectedRows <= 0) {
            throw new PersistenceException("No row was deleted.");
        }

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
        horse.setFavoriteSportName(resultSet.getString("favoriteSportName"));
        horse.setMotherId(getLongObject(resultSet, "mother"));
        horse.setFatherId(getLongObject(resultSet, "father"));
        horse.setMotherName(resultSet.getString("mothername"));
        horse.setFatherName(resultSet.getString("fathername"));
        return horse;
    }

    private Horse mapTreeRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setDescription(resultSet.getString("description"));
        horse.setSex(Sex.valueOf(resultSet.getString("sex")));
        horse.setBirthDay(resultSet.getDate("birthday").toLocalDate());
        horse.setFavoriteSportId(getLongObject(resultSet, "favoriteSport"));
        horse.setFavoriteSportName(resultSet.getString("favoriteSportName"));
        horse.setMotherId(getLongObject(resultSet, "mother"));
        horse.setFatherId(getLongObject(resultSet, "father"));
        return horse;
    }

    private HorseTree horsesToTree(Map<Long, Horse> horses, Long root) {
        if (root == null) return null;
        Horse horse = horses.get(root);
        if (horse == null) return null;
        HorseTree tree = new HorseTree();
        tree.setId(horse.getId());
        tree.setName(horse.getName());
        tree.setDescription(horse.getDescription());
        tree.setBirthDay(horse.getBirthDay());
        tree.setSex(horse.getSex());
        tree.setFavoriteSportId(horse.getFavoriteSportId());
        tree.setFavoriteSportName(horse.getFavoriteSportName());
        tree.setFather(horsesToTree(horses, horse.getFatherId()));
        tree.setMother(horsesToTree(horses, horse.getMotherId()));
        return tree;
    }

}
