package com.toni.bendreality.weeklymapping.repository;

import com.toni.bendreality.weeklymapping.model.WeeklyMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeeklyMappingRepository extends JpaRepository<WeeklyMapping,Integer> {

    Optional<WeeklyMapping> getWeeklyMappingByUserId(Integer userId);
}
