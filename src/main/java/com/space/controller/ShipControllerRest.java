package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.BadRequestException;
import com.space.service.IdNotFoundException;
import com.space.service.ShipFilter;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "rest")
public class ShipControllerRest {

    private ShipService shipService;

    @GetMapping(value = "ships", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ship>> getList(
            @RequestParam(required = false) String name, @RequestParam(required = false) String planet,
            @RequestParam(required = false) ShipType shipType, @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before, @RequestParam(required = false) Boolean isUsed,
            @RequestParam(required = false) Double minSpeed, @RequestParam(required = false) Double maxSpeed,
            @RequestParam(required = false) Integer minCrewSize, @RequestParam(required = false) Integer maxCrewSize,
            @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating,
            @RequestParam(required = false) ShipOrder order, @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        ShipFilter filter = new ShipFilter();
        filter.setName(name)
                .setPlanet(planet)
                .setShipType(shipType)
                .setAfter(after)
                .setBefore(before)
                .setUsed(isUsed)
                .setMinSpeed(minSpeed)
                .setMaxSpeed(maxSpeed)
                .setMinCrewSize(minCrewSize)
                .setMaxCrewSize(maxCrewSize)
                .setMinRating(minRating)
                .setMaxRating(maxRating)
                .setOrder(order)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize);

        return new ResponseEntity<>(shipService.getPageableList(filter), HttpStatus.OK);
    }

    @GetMapping(value = "ships/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> getCount(
            @RequestParam(required = false) String name, @RequestParam(required = false) String planet,
            @RequestParam(required = false) ShipType shipType, @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before, @RequestParam(required = false) Boolean isUsed,
            @RequestParam(required = false) Double minSpeed, @RequestParam(required = false) Double maxSpeed,
            @RequestParam(required = false) Integer minCrewSize, @RequestParam(required = false) Integer maxCrewSize,
            @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating) {
        ShipFilter filter = new ShipFilter();
        filter.setName(name)
                .setPlanet(planet)
                .setShipType(shipType)
                .setAfter(after)
                .setBefore(before)
                .setUsed(isUsed)
                .setMinSpeed(minSpeed)
                .setMaxSpeed(maxSpeed)
                .setMinCrewSize(minCrewSize)
                .setMaxCrewSize(maxCrewSize)
                .setMinRating(minRating)
                .setMaxRating(maxRating);

        return new ResponseEntity<>(shipService.getCount(filter), HttpStatus.OK);
    }

    @PostMapping(value = "ships", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> createShip(@RequestBody @Validated Ship ship) {
        if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            ship = shipService.create(ship);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @GetMapping(value = "ships/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> getShip(@PathVariable("id") Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Ship ship = shipService.getById(id);
        if (ship == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @PostMapping(value = "ships/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> updateShip(@PathVariable("id") Long id, @RequestBody @Validated Ship ship) {
        if (id == null || id <= 0||ship==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            ship = shipService.update(id, ship);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @DeleteMapping(value = "ships/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ship> deleteShip(@PathVariable("id") Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        boolean result = shipService.delete(id);
        if (!result) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }
}
