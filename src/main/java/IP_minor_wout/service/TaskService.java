package IP_minor_wout.service;



import IP_minor_wout.domain.Task;
import IP_minor_wout.dto.SubtaskDTO;
import IP_minor_wout.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getTasks();
    void addTask(TaskDTO taskDTO);
    TaskDTO addTaskAPI(TaskDTO taskDTO);
    Task getTask(long id);
    public TaskDTO getTaskDTO(long id);
    void editTask(long id, TaskDTO taskDTO);
    public List<SubtaskDTO> getSubtasks(long id);
    void addSubtask(Task task, SubtaskDTO subtaskDTO);
    void deleteTask(long id);
    void deleteSubtask(long id, int subId);
}
