package com.example.freelance_platform.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.freelance_platform.dto.TaskDTO;
import com.example.freelance_platform.models.Task;
import com.example.freelance_platform.models.User;
import com.example.freelance_platform.repository.TaskRepository;
import com.example.freelance_platform.repository.UserRepository;

@Service

public class TaskService {


    public  String getEmail()
    {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Object principal = authentication.getPrincipal();
       if(principal instanceof UserDetails)
       {
           UserDetails userDetails = (UserDetails)principal;
           String email = userDetails.getUsername();
         
           return(email);
       }
       else
       {
        return(null);
       }
    }

    @Autowired
    TaskRepository taskRepository;
    // @Autowired
    // Task task;
    @Autowired
    UserRepository userRepository;
    // @Autowired
    // TaskDTO taskDTO;
    public boolean createTask(TaskDTO taskbody)
    {
        try{
              Task task = new Task();
             task.setName(taskbody.getName());
             task.setDescription(taskbody.getDescription());
             task.setDuration_in_hrs(taskbody.getDuration_in_hrs());
             task.setRemuneration(taskbody.getRemuneration());
             String email = getEmail();
             
            User retrieved_user = userRepository.findByEmail(email).get();
              System.out.println(retrieved_user.getPassword());
            if(retrieved_user != null)
            {
                task.setUser(retrieved_user);
                
            }
             taskRepository.save(task);
             return(true);

            
        }
        catch( Exception e)
        {
            System.out.println("Error while saving task into taskbody"+e.getMessage());
        }
        return(false);
        

    }
    
    public ArrayList<TaskDTO> viewTask()
    {
        String email = getEmail();
        ArrayList<Task> taskList = new ArrayList<>();
        taskList = taskRepository.findByUser_Email(email).get();
        ArrayList<TaskDTO>taskDTOList  = new ArrayList<>();
        for(Task task:taskList)
        {
            TaskDTO taskDTO  = new TaskDTO();
            //autowiring will not work , as it modifies the same object every time
            // because here references to object are stored not the complete object 
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDuration_in_hrs(task.getDuration_in_hrs());
            taskDTO.setName(task.getName());
            taskDTO.setRemuneration(task.getRemuneration());
            taskDTO.setId(task.getId());
           
            taskDTOList.add(taskDTO);
        }
        
        return(taskDTOList);

        

    }
    public boolean updateTask(TaskDTO taskDTO, Long id)
    {
        try{
       Task retrievedTask = taskRepository.findById(id).get();
       if(taskDTO.getName() != null)
       {
        retrievedTask.setName(taskDTO.getName());
       }
       if(taskDTO.getDescription() != null)
       {
        retrievedTask.setDescription(taskDTO.getDescription());
       }
       if(taskDTO.getDuration_in_hrs() != null)
       {
        retrievedTask.setDuration_in_hrs(taskDTO.getDuration_in_hrs());
       }
       if(taskDTO.getRemuneration()   != null)  
       {
        retrievedTask.setRemuneration(taskDTO.getRemuneration());
       }
       taskRepository.save(retrievedTask);
       return true;
    }
    catch(Exception e)
    {
        System.out.println("Exception ocuured while updating task"+e.getMessage());
    }
    return(false);
    }
    
    public boolean deleteTask(Long id)
{
    try
    {
    Task retrievedTask = taskRepository.findById( id).get();
    taskRepository.delete(retrievedTask);
    return true;
    }
    catch(Exception e)
    {
        System.out.println("Error while deleteting task from database"+e.getMessage());
    }
    return false;

}

    @Value("${filePath}")
    private String basePath;
    public boolean uploadFile(Long taskid, MultipartFile file)

    {
        String filename= "task_desc"+Long.toString(taskid);
        File dir = new File(basePath+filename);
        // even if dir exists , the new file will overwrite the old one,dir is file object
        System.out.println(basePath);
        Path path=  Path.of(basePath+filename);
        System.out.println(path);
       
        

        
        try{
               Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
               Task task_whose_jd_is_to_be_uploaded = taskRepository.findById(taskid).get();
               task_whose_jd_is_to_be_uploaded.setTask_document_link(path.toString());
               taskRepository.save(task_whose_jd_is_to_be_uploaded);
        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        return true;

    }

   
    
}


