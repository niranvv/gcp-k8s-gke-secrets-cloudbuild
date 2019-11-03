package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class TaskController {

    @GetMapping("/task")
    public String task(@RequestParam(value = "id", required = true) Integer id,Model model) {
        Task task = getTask(id);
        model.addAttribute("id", task.getId());
        model.addAttribute("taskDueDate", task.getTaskDueDate());
        model.addAttribute("documentDueDate", task.getDocumentDueDate());
        model.addAttribute("referenceNo", task.getReferenceNo());
        model.addAttribute("state", task.getState());
        model.addAttribute("claimantName", task.getClaimantName());
        model.addAttribute("companyName", task.getCompanyName());
        model.addAttribute("taskName", task.getTaskName());
        model.addAttribute("receivedDate", task.getReceivedDate());
        model.addAttribute("assingedTo", task.getAssingedTo());
        model.addAttribute("createdAt", task.getCreatedAt());
        return "task";
    }

    private Task getTask(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Task task = restTemplate.getForObject("http://35.239.149.39:8080/api/tasks/"+id, Task.class);
        return task;
    }

}