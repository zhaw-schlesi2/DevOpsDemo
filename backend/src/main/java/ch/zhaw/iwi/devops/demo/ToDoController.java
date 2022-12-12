package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ToDoController {

    private List<ToDo> todos = new ArrayList<ToDo>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.todos.add(new ToDo(1, "Neuer Job", "DevOps Engineer einstellen"));
        this.todos.add(new ToDo(2, "Geschäftsleitung", "Mit Präsentation von DevOps überzeugen"));
        this.todos.add(new ToDo(3, "Unit Tests", "Neues Projekt mit Unit Tests starten"));
        this.todos.add(new ToDo(4, "Deployment", "Jede Woche"));
        this.todos.add(new ToDo(5, "Organigramm", "Löschen"));
        System.out.println("Init Data");
    }

    @GetMapping("/test")
    public String ping() {
        return "ToDo app is up and running!";
    }

    @GetMapping("/services/ping")
    public String ping2() {
        String languageCode = "de";
        return "{ \"status\": \"ok\", \"userId\": \"admin"+ "\", \"languageCode\": \"" + languageCode + "\",\"version\": \"0.0.1" + "\"}";
    }

    @GetMapping("/count")
    public int count() {
        return this.todos.size();
    }

    @GetMapping("/services/todo")
    public List<PathListEntry<Integer>> todo() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var todo : this.todos) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(todo.getId(), "todoKey");
            entry.setName(todo.getTitle());
            entry.getDetails().add(todo.getDescription());
            result.add(entry);
        }
        return result;
    }

}
