package IP_minor_wout.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubtaskDTO {

    @Id
    @GeneratedValue
    private long id;
    @NotNull(message = "Name of subtask or description cannot be empty")
    @NotEmpty(message = "Name of subtask cannot or description cannot be empty")
    private String taskName, taskDescription;

//    public SubtaskDTO(String taskName, String taskDescription){
//        setTaskName(taskName);
//        setTaskDescription(taskDescription);
//    }
//
//    public SubtaskDTO(){
//
//    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {

        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public String toString(){
        return taskName + " - > " + taskDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
