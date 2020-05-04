package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class ShipSpecification {

    public static Specification<Ship> getSpecification(ShipFilter filter) {
        return Specification.where(withName(filter.getName()))
                .and(withPlanet(filter.getPlanet()))
                .and(withShipType(filter.getShipType()))
                .and(withAfter(filter.getAfter()))
                .and(withBefore(filter.getBefore()))
                .and(withIsUsed(filter.getUsed()))
                .and(withMinSpeed(filter.getMinSpeed()))
                .and(withMaxSpeed(filter.getMaxSpeed()))
                .and(withMinCrewSize(filter.getMinCrewSize()))
                .and(withMaxCrewSize(filter.getMaxCrewSize()))
                .and(withMinRating(filter.getMinRating()))
                .and(withMaxRating(filter.getMaxRating()));
    }

    private static Specification<Ship> withName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Ship> withPlanet(String planet) {
        return (root, query, cb) -> planet == null ? null : cb.like(root.get("planet"), "%" + planet + "%");
    }

    private static Specification<Ship> withShipType(ShipType shipType) {
        return (root, query, cb) -> shipType == null ? null : cb.equal(root.get("shipType"), shipType);
    }

    private static Specification<Ship> withAfter(Long after) {
        return (root, query, cb) -> {
            if (after == null) return null;
            Date date = new Date(after);
            return cb.greaterThanOrEqualTo(root.get("prodDate"), date);
        };
    }

    private static Specification<Ship> withBefore(Long before) {
        return (root, query, cb) -> {
            if (before == null) return null;
            Date date = new Date(before);
            return cb.lessThanOrEqualTo(root.get("prodDate"), date);
        };
    }

    private static Specification<Ship> withIsUsed(Boolean isUsed) {
        return (root, query, cb) -> isUsed == null ? null : cb.equal(root.get("isUsed"), isUsed);
    }

    private static Specification<Ship> withMinSpeed(Double minSpeed) {
        return (root, query, cb) -> minSpeed == null ? null : cb.ge(root.get("speed"), minSpeed);
    }

    private static Specification<Ship> withMaxSpeed(Double maxSpeed) {
        return (root, query, cb) -> maxSpeed == null ? null : cb.le(root.get("speed"), maxSpeed);
    }

    private static Specification<Ship> withMinCrewSize(Integer minCrewSize) {
        return (root, query, cb) -> minCrewSize == null ? null : cb.ge(root.get("crewSize"), minCrewSize);
    }

    private static Specification<Ship> withMaxCrewSize(Integer maxCrewSize) {
        return (root, query, cb) -> maxCrewSize == null ? null : cb.le(root.get("crewSize"), maxCrewSize);
    }

    private static Specification<Ship> withMinRating(Double minRating) {
        return (root, query, cb) -> minRating == null ? null : cb.ge(root.get("rating"), minRating);
    }

    private static Specification<Ship> withMaxRating(Double maxRating) {
        return (root, query, cb) -> maxRating == null ? null : cb.le(root.get("rating"), maxRating);
    }
}
