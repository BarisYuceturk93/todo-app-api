package com.example.todoapp.WorkItem;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface WorkItemMapper {

    WorkItemMapper INSTANCE = Mappers.getMapper((WorkItemMapper.class));

    WorkItem fromTaskCreateDtoToTask(final WorkItemCreateDto dto);

    WorkItemCreateDto fromTasktoTaskCreateDto(final WorkItem task);


}
