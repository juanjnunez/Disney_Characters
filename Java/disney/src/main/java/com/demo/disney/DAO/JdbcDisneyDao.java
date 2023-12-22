package com.demo.disney.DAO;

import com.demo.disney.model.Disney;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcDisneyDao implements DisneyDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcDisneyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Disney getDisneyById(int disneyId) {
        Disney disney = null;
        String sql = "SELECT * FROM disney WHERE id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, disneyId);
            if (results.next()) {
                disney = mapRowToDisney(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Unable to connect to server or database");
        }
        return disney;
    }


    @Override
    public Disney create(Disney character) {
        Disney newCharacter = null;
        String sql = "INSERT INTO disney (title, char_id) VALUES (?, ?) RETURNING id;";

        try {

            int newId = jdbcTemplate.queryForObject(sql, int.class, character.getTitle(), character.getCharId());
            newCharacter = getDisneyById(newId);


        } catch (CannotGetJdbcConnectionException e){
            System.out.println("Unable to connect to server or database");

        } catch (DataIntegrityViolationException e){
            System.out.println("Data integrity violation");;
        }

        return newCharacter;
    }

    private Disney mapRowToDisney (SqlRowSet rs) {
        Disney disney = new Disney();
        disney.setId(rs.getInt("id"));
        disney.setTitle(rs.getString("title"));
        disney.setCharId(rs.getInt("char_id"));
        return disney;
    }
}
