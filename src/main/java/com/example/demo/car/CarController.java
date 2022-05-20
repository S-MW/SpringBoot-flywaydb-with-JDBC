package com.example.demo.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping()
    public List<Car> getCars()
    {
        return carService.getCars();
    }

    @PostMapping("add_car")
    public ResponseEntity<?> insertCar(@RequestBody Car car)
    {
        return carService.insertCar(car);
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable Integer id) {
        return carService.getCar(id);
    }

    @DeleteMapping("{id}")
    public String deleteCar(@PathVariable Integer id){
        return carService.deleteCar(id);
    }

    @PutMapping("update_car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody Car updateCar)
    {
        return carService.updateCar(id,updateCar);
    }


}
