package IP_minor_wout;



import IP_minor_wout.dto.CreateUserDTO;
import IP_minor_wout.dto.SubtaskDTO;
import IP_minor_wout.dto.TaskDTO;
import IP_minor_wout.dto.UserDTO;
import IP_minor_wout.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class testTaskService {
    @Autowired
    private TaskService taskService;

    @Test
    public void testAddTask(){
        //create Task
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName("task");
        taskDTO.setTaskDescription("task description");
        taskDTO.setTaskDue(LocalDateTime.now());
        taskService.addTask(taskDTO);
        //get all tasks
        List<TaskDTO> tasks = taskService.getTasks();
        //check you get back that task
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1,tasks.size());
        TaskDTO task = taskService.getTaskDTO(0);
        assertNotNull(task);
        assertEquals("task", task.getTaskName());
        assertEquals("task description", task.getTaskDescription());
    }

    @Test
    public void testAddSubtak(){
        //create Task
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName("task");
        taskDTO.setTaskDescription("task description");
        taskDTO.setTaskDue(LocalDateTime.now());
        taskService.addTask(taskDTO);
        //create subtask
        SubtaskDTO subtaskDTO = new SubtaskDTO();
        subtaskDTO.setTaskName("subtask");
        subtaskDTO.setTaskDescription("subtask description");
        taskService.addSubtask(taskService.getTask(1),subtaskDTO);

        //get all subtasks
        List<SubtaskDTO> subtasks = taskService.getSubtasks(1);

        //check subtask created
        assertNotNull(subtasks);
        assertFalse(subtasks.isEmpty());
        assertEquals(1,subtasks.size());
        SubtaskDTO subtask = subtasks.get(0);
        assertEquals("subtask", subtask.getTaskName());
        assertEquals("subtask description", subtask.getTaskDescription());


    }

    @Test
    public void checkUser(){
        //create User
        UserDTO userDTO = new UserDTO();
        assertNotNull(userDTO);
        //create CreateUserDto
        CreateUserDTO createUserDTO = new CreateUserDTO();
        assertNotNull(createUserDTO);

        //ckeck userService

    }


}
