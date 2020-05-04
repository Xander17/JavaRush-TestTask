package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.ShipType;

public class ShipFilter {
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 3;
    private final ShipOrder DEFAULT_ORDER = ShipOrder.ID;

    private int criteriaCount;

    private String name;
    private String planet;
    private ShipType shipType;
    private Long after;
    private Long before;
    private Boolean isUsed;
    private Double minSpeed;
    private Double maxSpeed;
    private Integer minCrewSize;
    private Integer maxCrewSize;
    private Double minRating;
    private Double maxRating;
    private ShipOrder order;
    private Integer pageNumber;
    private Integer pageSize;

    public ShipFilter() {
        pageNumber = DEFAULT_PAGE_NUMBER;
        pageSize = DEFAULT_PAGE_SIZE;
        order = DEFAULT_ORDER;
    }

    public int size() {
        return criteriaCount;
    }

    public String getName() {
        return name;
    }

    public ShipFilter setName(String name) {
        if (name != null && this.name == null) criteriaCount++;
        this.name = name;
        return this;
    }

    public String getPlanet() {
        return planet;
    }

    public ShipFilter setPlanet(String planet) {
        if (planet != null && this.planet == null) criteriaCount++;
        this.planet = planet;
        return this;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public ShipFilter setShipType(ShipType shipType) {
        if (shipType != null && this.shipType == null) criteriaCount++;
        this.shipType = shipType;
        return this;
    }

    public Long getAfter() {
        return after;
    }

    public ShipFilter setAfter(Long after) {
        if (after != null && this.after == null) criteriaCount++;
        this.after = after;
        return this;
    }

    public Long getBefore() {
        return before;
    }

    public ShipFilter setBefore(Long before) {
        if (before != null && this.before == null) criteriaCount++;
        this.before = before;
        return this;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public ShipFilter setUsed(Boolean used) {
        if (used != null && this.isUsed == null) criteriaCount++;
        isUsed = used;
        return this;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public ShipFilter setMinSpeed(Double minSpeed) {
        if (minSpeed != null && this.minSpeed == null) criteriaCount++;
        this.minSpeed = minSpeed;
        return this;

    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public ShipFilter setMaxSpeed(Double maxSpeed) {
        if (maxSpeed != null && this.maxSpeed == null) criteriaCount++;
        this.maxSpeed = maxSpeed;
        return this;
    }

    public Integer getMinCrewSize() {
        return minCrewSize;
    }

    public ShipFilter setMinCrewSize(Integer minCrewSize) {
        if (minCrewSize != null && this.minCrewSize == null) criteriaCount++;
        this.minCrewSize = minCrewSize;
        return this;
    }

    public Integer getMaxCrewSize() {
        return maxCrewSize;
    }

    public ShipFilter setMaxCrewSize(Integer maxCrewSize) {
        if (maxCrewSize != null && this.maxCrewSize == null) criteriaCount++;
        this.maxCrewSize = maxCrewSize;
        return this;
    }

    public Double getMinRating() {
        return minRating;
    }

    public ShipFilter setMinRating(Double minRating) {
        if (minRating != null && this.minRating == null) criteriaCount++;
        this.minRating = minRating;
        return this;
    }

    public Double getMaxRating() {
        return maxRating;
    }

    public ShipFilter setMaxRating(Double maxRating) {
        if (maxRating != null && this.maxRating == null) criteriaCount++;
        this.maxRating = maxRating;
        return this;
    }

    public ShipOrder getOrder() {
        return order;
    }

    public ShipFilter setOrder(ShipOrder order) {
        if (order == null) order = DEFAULT_ORDER;
        this.order = order;
        return this;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public ShipFilter setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 0) this.pageNumber = DEFAULT_PAGE_NUMBER;
        else this.pageNumber = pageNumber;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public ShipFilter setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 0) this.pageSize = DEFAULT_PAGE_SIZE;
        else this.pageSize = pageSize;
        return this;
    }

    @Override
    public String toString() {
        return "ShipFilter{" +
                "DEFAULT_PAGE_NUMBER=" + DEFAULT_PAGE_NUMBER +
                ", DEFAULT_PAGE_SIZE=" + DEFAULT_PAGE_SIZE +
                ", criteriaCount=" + criteriaCount +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", after=" + after +
                ", before=" + before +
                ", isUsed=" + isUsed +
                ", minSpeed=" + minSpeed +
                ", maxSpeed=" + maxSpeed +
                ", minCrewSize=" + minCrewSize +
                ", maxCrewSize=" + maxCrewSize +
                ", minRating=" + minRating +
                ", maxRating=" + maxRating +
                ", order=" + order +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
