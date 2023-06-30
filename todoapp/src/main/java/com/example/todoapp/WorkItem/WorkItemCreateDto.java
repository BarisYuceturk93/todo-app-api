package com.example.todoapp.WorkItem;

public class WorkItemCreateDto {

    private String taskName;

    private String taskExplanation;

    private boolean isCompleted;

    private String updateDate;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskExplanation() {
        return taskExplanation;
    }

    public void setTaskExplanation(String taskExplanation) {
        this.taskExplanation = taskExplanation;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
