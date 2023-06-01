package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ProjectController {
    private Map<Integer, Project> projects = new HashMap<Integer, Project>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.projects.put(1,new Project(1, "1", "Project 1"));
        this.projects.put(2,new Project(2, "2", "Project 2"));
        this.projects.put(3,new Project(3, "3", "Project 3"));
        this.projects.put(4,new Project(4, "4", "Project 4"));
        this.projects.put(5,new Project(5, "5", "Project 5"));
        System.out.println("Init Data");
    }

    @GetMapping("/testProject")
    public String test() {
        return "Project app is up and running!";
    }

    @GetMapping("/Projectcount")
    public int count() {
        return this.projects.size();
    }

    @GetMapping("/services/projects")
    public List<PathListEntry<Integer>> project() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var projects : this.projects.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(projects.getId(), "projectKey");
            entry.setName(projects.getTitle());
            entry.getDetails().add(projects.getDescription());
            entry.setTooltip(projects.getDescription());
            result.add(entry);
        }
        return result;
    }

    @GetMapping("/services/projects/{id}")
    public Project getTodo(@PathVariable Integer id) {
        return this.projects.get(id);
    }

    @PostMapping("/services/projects")
    public void createTodo(@RequestBody Project project) {
        var newId = this.projects.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        project.setId(newId);
        this.projects.put(newId, project);
    }

    @PutMapping("/services/projects/{id}")
    public void createTodo(@PathVariable Integer id, @RequestBody Project project) {
        project.setId(id);
        this.projects.put(id, project);
    }

    @DeleteMapping("/services/projects/{key}")
    public Project deleteTodo(@PathVariable Integer id) {
        return this.projects.remove(id);
    }
}
