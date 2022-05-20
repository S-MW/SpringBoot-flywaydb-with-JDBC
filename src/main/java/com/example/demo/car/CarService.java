package com.example.demo.car;

import com.example.demo.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {


    private final CarDeo carDeo;


    public CarService(CarDeo carDeo) {
        this.carDeo = carDeo;
    }

    public ResponseEntity<?> insertCar(Car car) {

        // TODO: check if car exists :: (Done!)
         Optional<Car> car1 = carDeo.selectCarByName(car.name());

        if(car1.isPresent())
        {
            System.out.println("car found! ");
            return ResponseEntity.ok().body(car1);
        }else{
            System.out.println("car Not found! ");

            int result = carDeo.insertCar(car);

            return ResponseEntity.ok().body("Saved! , id: "+result);
        }
    }

    public List<Car> getCars() {

        return carDeo.getCars();
    }


    public Car getCar(int id) {
        return  carDeo.selectCarById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", id)));
    }

    public String deleteCar(Integer id) {
        int result = carDeo.deleteCar(id);
        if (result != 1) {
            return "oops something went wrong";
//            throw new IllegalStateException("oops something went wrong");
        }
        return "Deleted!";
    }

    public ResponseEntity<?> updateCar(Integer id, Car updateCar)
    {
        // check if car is Exist
        Car car = getCar(id);

        int result = carDeo.updateCar(id,updateCar);
        return ResponseEntity.ok().body("Update!");
    }
}
