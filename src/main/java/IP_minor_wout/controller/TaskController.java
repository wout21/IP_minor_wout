package IP_minor_wout.controller;

import IP_minor_wout.dto.SubtaskDTO;
import IP_minor_wout.dto.TaskDTO;
import IP_minor_wout.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService= taskService;
    }

    @GetMapping("/")
    public String index() {
        //redirect so that springs default sing in does not go back to show app.css
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTask(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.getTaskDTO(id));
        model.addAttribute("subtasks", taskService.getSubtasks(id));
        return "task";
    }

    @GetMapping("/tasks/new")
    public String getAddTask(Model model) {
        model.addAttribute("taskDTO", new TaskDTO());
        return "newtask";
    }

    @PostMapping("/tasks/new")
    public String addTask(@ModelAttribute @Valid TaskDTO task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "newtask";
        }
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("tasks/edit/{id:\\d}")
    public String editTaskPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.getTaskDTO(id));
        return "edittask";
    }
    @GetMapping("tasks/delete/{id:\\d}")
    public String deleteTask(Model model, @PathVariable("id") int id) {

        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/edit/{id:\\d}")
    public String editTask(@ModelAttribute TaskDTO task, @PathVariable("id") int id) {

        taskService.editTask(id, task);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id:\\d}/sub/create")
    public String showSubtask(Model model, @PathVariable("id") int id){
        model.addAttribute("task",taskService.getTaskDTO(id));
        model.addAttribute("subtask", new SubtaskDTO());
        return "subtask";
    }

    @PostMapping("/tasks/{id:\\d}/sub/create")
    public String addSubtask(@ModelAttribute SubtaskDTO subTask, @PathVariable("id") int id, BindingResult br){
        if(br.hasErrors()){
            return "subtask";
        }
        taskService.addSubtask(taskService.getTask(id),subTask);
        return "redirect:/tasks/"+ id;
    }
    @GetMapping("/tasks/{id:\\d}/sub/delete/{subid:\\d}")
    public String deleteSubtask(Model model, @PathVariable("id") int id, @PathVariable("subid") int subId) {

        taskService.deleteSubtask(id, subId);
        return "redirect:/tasks/" + id;
    }

}
