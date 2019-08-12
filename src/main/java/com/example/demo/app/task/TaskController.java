package com.example.demo.app.task;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

//import com.example.dbtest.domain.entity.UserInfo;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //INDEX
    @GetMapping
    public String task(TaskForm taskForm, Model model) {
    	
        taskForm.setNewTask(true);
        List<Task> list = taskService.findAll();
        
        model.addAttribute("list", list);
        model.addAttribute("title", "タスク一覧");

        return "task/index";
    }

    //INSERT
    @PostMapping("/save")
    public String insert(
    	@Valid @ModelAttribute TaskForm taskForm,
        BindingResult result,
        Model model,
        Principal principal) {
    	
    	int userId = 1;
//    	if(principal !=  null) {//
//        	Authentication auth = (Authentication)principal;
//            UserInfo userInfo = (UserInfo)auth.getPrincipal();
//            userId = userInfo.getId();
//        }

        Task task = new Task();
        task.setUserId(1);
        task.setTypeId(taskForm.getTypeId());
        task.setTitle(taskForm.getTitle());
        task.setDetail(taskForm.getDetail());
        task.setDeadline(taskForm.getDeadline());
        
        if (!result.hasErrors()) {
            taskService.save(task);
            return "redirect:/task?complete";
        } else {
            taskForm.setNewTask(true);
            model.addAttribute("taskForm", taskForm);
            List<Task> list = taskService.findAll();
            model.addAttribute("list", list);
            model.addAttribute("title", "タスク一覧（バリデーション）");
            return "task/index";
        }
    }

    //Before UPDATE
    @GetMapping("/{id}")
    public String showUpdate(
    	TaskForm taskForm,
        @PathVariable int id,
        Model model) {

        Optional<TaskForm> form = taskService.getTaskForm(id);

        if (!form.isPresent()) {
            return "redirect:/task";
        }

        model.addAttribute("taskForm", form.get());
        List<Task> list = taskService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("taskId", id);
        model.addAttribute("title", "更新用フォーム");

        return "task/index";
    }
    
    /**
     * UPDATE
     * @param id
     * @param taskForm
     * @param mav
     * @return
     */
    @PostMapping("/update")
    public String update(
    	@Valid @ModelAttribute TaskForm taskForm,
    	BindingResult result,
    	@RequestParam("taskId") String id,
    	Model model,
    	RedirectAttributes redirectAttributes,
        Principal principal) {
    	
    	int taskId = Integer.parseInt(id);
    	int userId = 1;
//    	if(principal !=  null) {
//        	Authentication auth = (Authentication)principal;
//            UserInfo userInfo = (UserInfo)auth.getPrincipal();
//            userId = userInfo.getId();
//        }
    	
    	//isNewTaskはfalseに設定される
        Optional<TaskForm> form = taskService.getTaskForm(taskId);

        if (!form.isPresent()) {
            return "redirect:/task";
        }
    	
        Task task = new Task();
        task.setId(taskId);
        task.setUserId(1);
        task.setTypeId(taskForm.getTypeId());
        task.setTitle(taskForm.getTitle());
        task.setDetail(taskForm.getDetail());
        task.setDeadline(taskForm.getDeadline());
    	
        if (!result.hasErrors()) {
        	taskService.update(task);
        	redirectAttributes.addFlashAttribute("complete", "変更が完了しました");
            return "redirect:/task/" + taskId;
        } else {
            model.addAttribute("taskForm", taskForm);
            model.addAttribute("title", "タスク一覧");
            return "task/index";
        }
        
        
    }

    /**
     * DELETE
     * @param id
     * @param mav
     * @return
     */
    @PostMapping("/delete")
    public String delete(
    	@RequestParam("taskId") String id,
    	Model model) {
    	
        taskService.deleteById(Integer.parseInt(id));
        return "redirect:/task";
    }

//    private Task makeNewTask(int userId, TaskForm taskForm) {
//        return new Task(userId, taskForm.getTypeId(), taskForm.getTitle(), taskForm.getDetail(), taskForm.getDeadline());
//    }
//
//    private Task makeTask(int userId, TaskForm taskForm) {
//        return new Task(taskForm.getId(), userId, taskForm.getTypeId(), taskForm.getTitle(), taskForm.getDetail(), taskForm.getDeadline());
//    }


}