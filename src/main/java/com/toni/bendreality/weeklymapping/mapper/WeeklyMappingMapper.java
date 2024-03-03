package com.toni.bendreality.weeklymapping.mapper;

import com.toni.bendreality.dailyaction.model.DailyAction;
import com.toni.bendreality.dailyaction.model.DailyActionDTO;
import com.toni.bendreality.dailyaction.model.DailyActionViewDTO;
import com.toni.bendreality.weeklymapping.model.WeeklyMapping;
import com.toni.bendreality.weeklymapping.model.WeeklyMappingDTO;
import com.toni.bendreality.weeklymapping.model.WeeklyMappingViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public abstract class WeeklyMappingMapper {

    public abstract WeeklyMappingDTO toDTO(WeeklyMapping weeklyMapping);

    @Mapping(source="userId",target="user.id")
    @Mapping(source="userId",target="createdBy")
    public abstract WeeklyMapping toEntity(WeeklyMappingDTO weeklyMappingDTO);

    public abstract WeeklyMappingViewDTO toViewDTO(WeeklyMapping weeklyMapping);

    public abstract WeeklyMapping toEntity(WeeklyMappingViewDTO weeklyMappingViewDTO);


}
