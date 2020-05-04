package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ShipRepository extends JpaRepositoryImplementation<Ship, Long> {

}
