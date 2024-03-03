package com.toni.bendreality.dailyaction.repository;

import com.toni.bendreality.dailyaction.model.DailyAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyActionRepository extends JpaRepository<DailyAction,Integer> {
    Optional<DailyAction> getDailyActionByUserId(Integer userId);
}
