package com.example.demo.car;

import java.util.List;
import java.util.Optional;

public interface CarDeo {

    int insertCar(Car car);

    List<Car> getCars();


    Optional<Car> selectCarById(int id);

    int deleteCar(Integer id);

    Optional<Car> selectCarByName(String name);

    int updateCar(Integer id, Car updateCar);
}
