package com.isep.acme.mapper;

import org.mapstruct.Mapper;

import com.isep.acme.model.User;
import com.isep.acme.model.UserView;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
