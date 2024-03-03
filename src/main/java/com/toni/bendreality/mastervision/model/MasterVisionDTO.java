package com.toni.bendreality.mastervision.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public record MasterVisionDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,

        @NotBlank
        String masterVision,

        @NotBlank
        String identityDesign,

        @NotBlank
        String bucketList,

        @NotBlank
        String lifestyleDesign,

        @NotBlank
        String materialDesires,

        @NotBlank
        String northStarGoalOne,

        @NotBlank
        String northStarGoalTwo,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer userId
) {

}
