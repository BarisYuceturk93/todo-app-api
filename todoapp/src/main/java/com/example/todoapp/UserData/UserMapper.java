package com.example.todoapp.UserData;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper((UserMapper.class));

    UserData fromUserRegisterationDtoToUser(final UserRegistrationDto dto);

    UserRegistrationDto fromUserToUserRegisterationDto(final UserData user);
}
