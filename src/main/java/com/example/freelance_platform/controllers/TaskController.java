package com.example.freelance_platform.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.freelance_platform.dto.TaskDTO;
import com.example.freelance_platform.models.Task;
import com.example.freelance_platform.service.TaskService;

import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/api/client")
public class TaskController {
    @Autowired
    TaskService taskService;
   
    @PostMapping("/create-task")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO)

    {
        System.out.println(taskDTO.getName());
        boolean  flag = taskService.createTask(taskDTO);
        if(flag)
        {
            return(new ResponseEntity<>("Task created",HttpStatus.OK));
        }
        else
        {
            return(new ResponseEntity<>("Task creation failed",HttpStatus.OK));
        }

    }
    @GetMapping("/view-task")
    public ResponseEntity<?> viewTask()

    {
        ArrayList<TaskDTO> taskList  = taskService.viewTask();
        if(taskList != null)
        {
            return(new ResponseEntity<>(taskList,HttpStatus.OK));
        }
        else
        {
            return(new ResponseEntity<>("Task creation failed",HttpStatus.OK));
        }

    }
    @PatchMapping("/update-task")
    public ResponseEntity<String> updateTask(@RequestBody TaskDTO taskDTO,@RequestParam Long id )

    {
        boolean  flag = taskService.updateTask(taskDTO,id);
        if(flag)
        {
            return(new ResponseEntity<>("Task updated",HttpStatus.OK));
        }
        else
        {
            return(new ResponseEntity<>("Task updation failed",HttpStatus.OK));
        }

    }
    @DeleteMapping("/delete-task")
    public ResponseEntity<String> createTask(@RequestParam Long id)

    {
        boolean  flag = taskService.deleteTask(id);
        if(flag)
        {
            return(new ResponseEntity<>("Task deleted",HttpStatus.OK));
        }
        else
        {
            return(new ResponseEntity<>("Task deletion failed",HttpStatus.OK));
        }

    }
    
    
    

    
}
