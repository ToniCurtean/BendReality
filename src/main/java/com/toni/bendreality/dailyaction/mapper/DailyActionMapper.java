package com.toni.bendreality.dailyaction.mapper;

import com.toni.bendreality.dailyaction.model.DailyAction;
import com.toni.bendreality.dailyaction.model.DailyActionDTO;
import com.toni.bendreality.dailyaction.model.DailyActionViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public abstract class DailyActionMapper {

    public abstract DailyActionDTO toDTO(DailyAction dailyAction);

    @Mapping(source="userId",target="user.id")
    @Mapping(source="userId",target="createdBy")
    public abstract DailyAction toEntity(DailyActionDTO dailyActionDTO);

    public abstract DailyActionViewDTO toViewDTO(DailyAction dailyAction);

    public abstract DailyAction toEntity(DailyActionViewDTO dailyActionViewDTO);

}
