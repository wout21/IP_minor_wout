package IP_minor_wout.rest.controller;

import IP_minor_wout.domain.Task;
import IP_minor_wout.dto.TaskDTO;
import IP_minor_wout.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks()
    {
        return taskService.getTasks();
    }


    @GetMapping("/tasks/{id}")
    @ResponseBody
    public Task getTask(@PathVariable("id") int id)
    {
        return taskService.getTask(id);
    }

    @PostMapping("/tasks/new")
    public TaskDTO newTask(@RequestBody @Valid TaskDTO taskDTO){
        return taskService.addTaskAPI(taskDTO);
    }



}
