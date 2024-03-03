package com.toni.bendreality.dailyaction.model;

import java.time.LocalDate;

public record DailyActionViewDTO(
        Integer id,
        String visionObjOne,
        String visionObjTwo,
        String visionObjThree,
        String mindsetObj,
        String bodyObj,
        String socialObj,
        String gratefulFor,

        LocalDate createdAt,

        LocalDate updatedAt,
        Integer createdBy) {
}
