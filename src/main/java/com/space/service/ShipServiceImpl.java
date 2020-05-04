package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private ShipRepository shipRepository;


    @Override
    public List<Ship> getFullList(ShipFilter filter) {
        if (filter == null) filter = new ShipFilter();
        return shipRepository.findAll(ShipSpecification.getSpecification(filter));
    }

    @Override
    public List<Ship> getPageableList(ShipFilter filter) {
        if (filter == null) filter = new ShipFilter();
        Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize(), Sort.Direction.ASC, filter.getOrder().getFieldName());
        return shipRepository.findAll(ShipSpecification.getSpecification(filter), pageable).get().collect(Collectors.toList());
    }

    @Override
    public Ship create(Ship ship) throws BadRequestException {
        if (!ShipValidator.checkShipName(ship.getName())
                || !ShipValidator.checkShipPlanet(ship.getPlanet())
                || !ShipValidator.checkShipType(ship.getShipType())
                || !ShipValidator.checkShipProdDate(ship.getProdDate())
                || !ShipValidator.checkShipSpeed(ship.getSpeed())
                || !ShipValidator.checkShipCrewSize(ship.getCrewSize()))
            throw new BadRequestException();
        if (ship.getUsed() == null) ship.setUsed(false);
        ship.setName(ship.getName().trim());
        ship.setPlanet(ship.getPlanet().trim());
        ship.setId(null);
        ship.setRating(null);
        recalculateRating(ship);
        shipRepository.save(ship);
        return ship;
    }

    @Override
    public Ship update(Long id, Ship ship) throws IdNotFoundException, BadRequestException {
        Ship savedShip = getById(id);
        if (savedShip == null) throw new IdNotFoundException();

        String name = ship.getName();
        if (name != null) name = name.trim();
        String planet = ship.getPlanet();
        if (planet != null) planet = planet.trim();
        ShipType type = ship.getShipType();
        Date date = ship.getProdDate();
        Boolean isUsed = ship.getUsed();
        Double speed = ship.getSpeed();
        Integer crewSize = ship.getCrewSize();

        if (ShipValidator.checkShipName(name)) savedShip.setName(name);
        else if (name != null) throw new BadRequestException();
        if (ShipValidator.checkShipPlanet(planet)) savedShip.setPlanet(planet);
        else if (planet != null) throw new BadRequestException();
        if (ShipValidator.checkShipType(type)) savedShip.setShipType(type);
        else if (type != null) throw new BadRequestException();
        if (ShipValidator.checkShipProdDate(date)) savedShip.setProdDate(date);
        else if (date != null) throw new BadRequestException();
        if (ShipValidator.checkIsUsed(isUsed)) savedShip.setUsed(isUsed);
        else if (isUsed != null) throw new BadRequestException();
        if (ShipValidator.checkShipSpeed(speed)) savedShip.setSpeed(speed);
        else if (speed != null) throw new BadRequestException();
        if (ShipValidator.checkShipCrewSize(crewSize)) savedShip.setCrewSize(crewSize);
        else if (crewSize != null) throw new BadRequestException();

        recalculateRating(savedShip);

        shipRepository.save(savedShip);
        return savedShip;
    }

    @Override
    public boolean delete(Long id) {
        if (!shipRepository.existsById(id)) return false;
        shipRepository.deleteById(id);
        return true;
    }

    @Override
    public Ship getById(Long id) {
        return shipRepository.findById(id).orElse(null);
    }

    @Override
    public Integer getCount(ShipFilter filter) {
        return getFullList(filter).size();
    }

    @Autowired
    public void setShipRepository(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    private void recalculateRating(Ship ship) {
        int yearNow = 3019;
        double speed = ship.getSpeed();
        double usedCoefficient = ship.getUsed() ? 0.5 : 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ship.getProdDate());
        int prodYear = calendar.get(Calendar.YEAR);
        ship.setRating(Math.round(100 * (80 * speed * usedCoefficient) / (yearNow - prodYear + 1)) / 100.);
    }
}
