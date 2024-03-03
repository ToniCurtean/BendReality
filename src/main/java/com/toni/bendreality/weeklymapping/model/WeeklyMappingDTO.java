package com.toni.bendreality.weeklymapping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Valid
public record WeeklyMappingDTO(
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

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer userId
) {

}
