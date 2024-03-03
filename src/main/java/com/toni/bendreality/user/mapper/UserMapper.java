package com.toni.bendreality.user.mapper;

import com.toni.bendreality.user.model.User;
import com.toni.bendreality.user.model.UserDTO;
import com.toni.bendreality.user.model.UserViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public abstract UserDTO toDTO(User user);

    public abstract User toEntity(UserDTO user);

    public abstract UserViewDTO toViewDTO(User user);

    public abstract User toEntity(UserViewDTO userViewDTO);
}
