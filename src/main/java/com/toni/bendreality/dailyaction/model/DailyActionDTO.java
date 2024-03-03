package com.toni.bendreality.dailyaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Valid
public record DailyActionDTO(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,

        @NotBlank
        String visionObjOne,

        @NotBlank
        String visionObjTwo,

        @NotBlank
        String visionObjThree,

        @NotBlank
        String mindsetObj,

        @NotBlank
        String bodyObj,

        @NotBlank
        String socialObj,

        @NotBlank
        String gratefulFor,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer userId
) {

}
