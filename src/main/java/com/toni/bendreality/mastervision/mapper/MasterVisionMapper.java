package com.toni.bendreality.mastervision.mapper;

import com.toni.bendreality.mastervision.model.MasterVision;
import com.toni.bendreality.mastervision.model.MasterVisionDTO;
import com.toni.bendreality.mastervision.model.MasterVisionViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MasterVisionMapper {

    public abstract MasterVisionDTO toDTO(MasterVision masterVision);

    @Mapping(source="userId",target="user.id")
    @Mapping(source="userId",target="createdBy")
    public abstract MasterVision toEntity(MasterVisionDTO masterVisionDTO);

    public abstract MasterVisionViewDTO toViewDTO(MasterVision masterVision);

    public abstract MasterVision toEntity(MasterVisionViewDTO masterVisionViewDTO);
}
