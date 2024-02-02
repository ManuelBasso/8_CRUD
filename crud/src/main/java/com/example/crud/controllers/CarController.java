package com.example.crud.controllers;

import com.example.crud.entities.Car;
import com.example.crud.repositories.CarRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// DONE - crea nuova Car
// DONE - restituisce la lista di tutte le Car
// DONE - restituisce una singola Car - se id non è presente in db (usa existsById()), restituisce Car vuota
// DONE - aggiorna type della Car specifica, identificata da id e passando query param se id non è presente in db (usa existsById()), restituisce Car vuota
//cancella la Car specifica - se non presente, la risposta deve avere Conflict HTTP status
// DONE - cancella tutte le Cars in db

@RestController
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    CarRepositery carRepositery;

    @PostMapping(path = "/create")
    public Car createCar(@RequestBody Car car) {
        return carRepositery.saveAndFlush(car);
    }

    @GetMapping(path = "/getAll")
    public List<Car> getAll() {
        return carRepositery.findAll();
    }

    @GetMapping(path = "/getById/{idToFind}")
    public Car getById(@PathVariable long idToFind) {
        if (carRepositery.existsById(idToFind)) {
            return carRepositery.getReferenceById(idToFind);
        } else {
            return new Car();
        }
    }


    @PutMapping(path = "/updateById/{id}")
    public Car updateCar(@PathVariable long id, @RequestParam String type) {
        if (carRepositery.existsById(id)) {
            Car c = carRepositery.getReferenceById(id);
            c.setType(type);
            carRepositery.saveAndFlush(c);
            return c;
        } else {
            return new Car();
        }
    }
    //cancella la Car specifica - se non presente, la risposta deve avere Conflict HTTP status

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        if (carRepositery.existsById(id)) {
            carRepositery.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/deleteAll")
    public void deleteAll() {
        carRepositery.deleteAll();
    }

}
