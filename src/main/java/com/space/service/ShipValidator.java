package com.space.service;

import com.space.model.ShipType;

import java.util.Date;

public class ShipValidator {

    public static boolean checkShipName(String name) {
        return name != null
                && !name.trim().isEmpty()
                && name.trim().length() <= 50;
    }

    public static boolean checkShipPlanet(String planet) {
        return planet != null
                && !planet.trim().isEmpty()
                && planet.trim().length() <= 50;
    }

    public static boolean checkShipType(ShipType type) {
        return type != null;
    }

    public static boolean checkShipProdDate(Date date) {
        return date != null
                && date.getTime() >= 0;
    }

    public static boolean checkIsUsed(Boolean used) {
        return used != null;
    }

    public static boolean checkShipSpeed(Double speed) {
        return speed != null
                && Math.round(speed * 100) / 100. >= 0.01
                && Math.round(speed * 100) / 100. <= 0.99;
    }

    public static boolean checkShipCrewSize(Integer crewSize) {
        return crewSize != null
                && crewSize >= 1
                && crewSize <= 9999;
    }
}
