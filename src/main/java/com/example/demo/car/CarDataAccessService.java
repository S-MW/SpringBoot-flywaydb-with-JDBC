package com.example.demo.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDataAccessService implements CarDeo{

    private final JdbcTemplate jdbcTemplate;

    public CarDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCar(Car car) {

        var sql = """
                INSERT INTO car(name, color)
                VALUES (?, ?);
                 """;


        KeyHolder keyHolder = new GeneratedKeyHolder();
         jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        ps.setString(1, car.name());
                        ps.setString(2, car.color());
                        return ps;
                    }
                },
                keyHolder);

        return keyHolder.getKey().intValue();

//        return jdbcTemplate.update(
//                sql,
//                car.name(), car.color()
//        );
    }

    @Override
    public List<Car> getCars() {

        var sql = """
                SELECT * 
                FROM car 
                LIMIT 1000; 
                """;
        return jdbcTemplate.query(sql,new CarRowMapper());
    }

    @Override
    public Optional<Car> selectCarById(int id) {
        var sql = """
                SELECT * 
                FROM car
                WHERE id = ?
                 """;
        return jdbcTemplate.query(sql, new CarRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public int deleteCar(Integer id) {
        var sql = """
                DELETE FROM car 
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public Optional<Car> selectCarByName(String name)
    {
        var sql = """
                SELECT *
                FROM car
                WHERE name = ?
                """;
        return jdbcTemplate.query(sql,new CarRowMapper(),name)
                .stream()
                .findFirst();
    }

    @Override
    public int updateCar(Integer id, Car updateCar) {

//        var sql = """
//                UPDATE car
//                SET name = ? ,color = ?
//                WHERE id = ?
//                """;
        return jdbcTemplate.update("UPDATE car SET name = ?, color = ? WHERE id = ?"
                , new Object[] {updateCar.name(), updateCar.color(), id});
    }


}
