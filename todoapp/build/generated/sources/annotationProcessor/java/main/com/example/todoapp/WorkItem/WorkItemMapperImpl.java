package com.example.todoapp.WorkItem;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-30T09:17:34+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class WorkItemMapperImpl implements WorkItemMapper {

    @Override
    public WorkItem fromTaskCreateDtoToTask(WorkItemCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        WorkItem workItem = new WorkItem();

        workItem.setTaskName( dto.getTaskName() );
        workItem.setTaskExplanation( dto.getTaskExplanation() );
        workItem.setCompleted( dto.isCompleted() );
        workItem.setUpdateDate( dto.getUpdateDate() );

        return workItem;
    }

    @Override
    public WorkItemCreateDto fromTasktoTaskCreateDto(WorkItem task) {
        if ( task == null ) {
            return null;
        }

        WorkItemCreateDto workItemCreateDto = new WorkItemCreateDto();

        workItemCreateDto.setTaskName( task.getTaskName() );
        workItemCreateDto.setTaskExplanation( task.getTaskExplanation() );
        workItemCreateDto.setCompleted( task.isCompleted() );
        workItemCreateDto.setUpdateDate( task.getUpdateDate() );

        return workItemCreateDto;
    }
}
