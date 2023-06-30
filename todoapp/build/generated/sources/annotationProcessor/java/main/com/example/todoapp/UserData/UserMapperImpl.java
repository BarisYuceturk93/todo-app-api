package com.example.todoapp.UserData;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-30T09:17:34+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserData fromUserRegisterationDtoToUser(UserRegistrationDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserData userData = new UserData();

        userData.setName( dto.getName() );
        userData.setSurname( dto.getSurname() );
        userData.setUsername( dto.getUsername() );
        userData.setPassword( dto.getPassword() );
        userData.setEmail( dto.getEmail() );
        Set<String> set = dto.getRoles();
        if ( set != null ) {
            userData.setRoles( new LinkedHashSet<String>( set ) );
        }

        return userData;
    }

    @Override
    public UserRegistrationDto fromUserToUserRegisterationDto(UserData user) {
        if ( user == null ) {
            return null;
        }

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        userRegistrationDto.setName( user.getName() );
        userRegistrationDto.setSurname( user.getSurname() );
        userRegistrationDto.setUsername( user.getUsername() );
        userRegistrationDto.setPassword( user.getPassword() );
        userRegistrationDto.setEmail( user.getEmail() );
        Set<String> set = user.getRoles();
        if ( set != null ) {
            userRegistrationDto.setRoles( new LinkedHashSet<String>( set ) );
        }

        return userRegistrationDto;
    }
}
