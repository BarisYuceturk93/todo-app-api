package com.example.todoapp.WorkItem;

import com.example.todoapp.UserData.UserData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class WorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    private String taskExplanation;

    private boolean isCompleted;

    private String updateDate;

    private String createDate;

    @JsonBackReference
    @ManyToMany
    private List<UserData> userlist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<UserData> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserData> userlist) {
        this.userlist = userlist;
    }

    public WorkItem() {
    }

    public WorkItem(Long id, String taskName, String taskExplanation, boolean isCompleted, String updateDate, String createDate, List<UserData> userlist) {
        this.id = id;
        this.taskName = taskName;
        this.taskExplanation = taskExplanation;
        this.isCompleted = isCompleted;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.userlist = userlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkItem task = (WorkItem) o;
        return isCompleted == task.isCompleted && id.equals(task.id) && taskName.equals(task.taskName) && taskExplanation.equals(task.taskExplanation) && updateDate.equals(task.updateDate) && createDate.equals(task.createDate) && userlist.equals(task.userlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, taskExplanation, isCompleted, updateDate, createDate, userlist);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskExplanation='" + taskExplanation + '\'' +
                ", isCompleted=" + isCompleted +
                ", updateDate='" + updateDate + '\'' +
                ", createDate='" + createDate + '\'' +
                ", userlist=" + userlist +
                '}';
    }

}
