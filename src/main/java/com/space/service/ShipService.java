package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {

    List<Ship> getFullList(ShipFilter filter);
    List<Ship> getPageableList(ShipFilter filter);

    Ship create(Ship ship) throws BadRequestException;

    Ship update(Long id, Ship ship) throws IdNotFoundException, BadRequestException;

    boolean delete(Long id);

    Ship getById(Long id);

    Integer getCount(ShipFilter filter);

}
