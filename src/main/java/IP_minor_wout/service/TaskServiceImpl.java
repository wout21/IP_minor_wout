package IP_minor_wout.service;


import IP_minor_wout.domain.Subtask;
import IP_minor_wout.domain.Task;
import IP_minor_wout.dto.SubtaskDTO;
import IP_minor_wout.dto.TaskDTO;
import IP_minor_wout.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository){
        this.repository = repository;
    }


    @Override
    public List<TaskDTO> getTasks() {
        return repository.findAll().stream().map(t -> {
            TaskDTO dto = new TaskDTO();
            dto.setTaskName(t.getTaskName());
            dto.setTaskDescription(t.getTaskDescription());
            dto.setTaskDue(t.getTaskDue());
            dto.setId(t.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskDue(taskDTO.getTaskDue());
        repository.save(task);
        repository.flush();
    }

    @Override
    public TaskDTO addTaskAPI(TaskDTO taskDTO) {
        addTask(taskDTO);
        return taskDTO;
    }

    @Override
    public Task getTask(long id) {
        return repository.getOne(id);
    }

    @Override
    public TaskDTO getTaskDTO(long id) {
        List<Task> ts = repository.findAll();
        Task t = repository.getOne(id);
        TaskDTO dto = new TaskDTO();
        dto.setTaskName(t.getTaskName());
        dto.setTaskDescription(t.getTaskDescription());
        dto.setTaskDue(t.getTaskDue());
        dto.setId(t.getId());
        return dto;
    }

    @Override
    public void editTask(long id, TaskDTO taskDTO) {
        repository.getOne(id).setTaskDue(taskDTO.getTaskDue());
        repository.getOne(id).setTaskName(taskDTO.getTaskName());
        repository.getOne(id).setTaskDescription(taskDTO.getTaskDescription());
        repository.flush();
    }

    @Override
    public void addSubtask(Task task, SubtaskDTO subtaskDTO) {
        Subtask subtask = new Subtask();
        subtask.setTaskName(subtaskDTO.getTaskName());
        subtask.setTaskDescription(subtaskDTO.getTaskDescription());
        task.addSubTask(subtask);
        repository.save(task);
        repository.flush();
    }


    @Override
    public void deleteTask(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteSubtask(long id, int subId) {
        Task task = repository.getOne(id);
        task.deleteSubtask(subId);
        repository.save(task);
        repository.flush();
    }

    @Override
    public List<SubtaskDTO> getSubtasks(long id) {
       return getTask(id).getSubtasks().stream().map(t -> {
            SubtaskDTO dto = new SubtaskDTO();
            dto.setTaskName(t.getTaskName());
            dto.setTaskDescription(t.getTaskDescription());
            dto.setId(t.getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
